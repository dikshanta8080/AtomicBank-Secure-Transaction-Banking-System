package com.banking.sathi.controller;

import com.banking.sathi.enums.Role;
import com.banking.sathi.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null || user.getRole() == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            request.getSession().setAttribute("error", "Invalid account id");
            response.sendRedirect(request.getContextPath() + "/user/dashboard");
            return;
        }

        try {
            Long accountId = Long.valueOf(idParam);

            // TODO: replace with real account lookup once Account DAO/Service exists.
            request.setAttribute("accountId", accountId);

            request.getRequestDispatcher("/WEB-INF/jsp/user/account.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid account id format");
            response.sendRedirect(request.getContextPath() + "/user/dashboard");
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Unable to load account");
            response.sendRedirect(request.getContextPath() + "/user/dashboard");
        }
    }
}