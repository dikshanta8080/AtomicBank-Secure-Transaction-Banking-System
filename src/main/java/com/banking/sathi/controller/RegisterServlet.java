package com.banking.sathi.controller;

import com.banking.sathi.exceptions.UserAlreadyExistsException;
import com.banking.sathi.model.User;
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

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = new User(name, email, password);

            UserValidator.validateCredentialsForRegistration(user);

            boolean isRegistered = authService.registerUser(user);

            if (isRegistered) {
                HttpSession session = req.getSession();
                session.setAttribute("success", "Account created successfully. Please log in.");

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/login");

            } else {
                req.setAttribute("error", "Registration failed. Please try again.");
                req.getRequestDispatcher("views/register.jsp").forward(req, resp);
            }

        } catch (IllegalArgumentException | UserAlreadyExistsException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("views/register.jsp").forward(req, resp);
        }
    }
}
