package com.banking.sathi.controller;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.enums.Role;
import com.banking.sathi.exceptions.AuthenticationFailedException;
import com.banking.sathi.exceptions.UserDoesnotExistsException;
import com.banking.sathi.model.User;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.service.AuthService;
import com.banking.sathi.validators.UserValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;
    private AccountRepository accountRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.authService = new AuthService();
        this.accountRepository = new AccountDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            UserValidator.validateCredentialsForLogin(email, password);

            User user = authService.login(email, password);

            if (user == null) {
                throw new UserDoesnotExistsException("Invalid email or password");
            }

            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = req.getSession(true);
            newSession.setAttribute("user", user);

            String contextPath = req.getContextPath();

            if (user.getRole() == Role.ADMIN) {
                resp.sendRedirect(contextPath + "/admin/dashboard");
            } else {
                if (accountRepository.existsByUserId(user.getId())) {
                    resp.sendRedirect(contextPath + "/user/dashboard");
                } else {
                    resp.sendRedirect(contextPath + "/account");
                }
            }

        } catch (IllegalArgumentException | UserDoesnotExistsException | AuthenticationFailedException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("views/auth/login.jsp").forward(req, resp);
        }
    }
}