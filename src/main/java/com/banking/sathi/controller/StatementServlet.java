package com.banking.sathi.controller;

import com.banking.sathi.model.User;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/statement")
public class StatementServlet extends HttpServlet {
    private PortalService portalService;

    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        LocalDate fromDate = parseDate(req.getParameter("fromDate"));
        LocalDate toDate = parseDate(req.getParameter("toDate"));
        req.setAttribute("account", portalService.getAccountByUserId(user.getId()).orElse(null));
        req.setAttribute("transactions", portalService.getUserTransactions(user.getId(), fromDate, toDate));
        req.setAttribute("fromDate", req.getParameter("fromDate"));
        req.setAttribute("toDate", req.getParameter("toDate"));
        req.getRequestDispatcher("/views/user/statement.jsp").forward(req, resp);
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return LocalDate.parse(value);
    }
}
