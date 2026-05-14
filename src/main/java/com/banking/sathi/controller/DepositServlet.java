package com.banking.sathi.controller;

import com.banking.sathi.model.User;
import com.banking.sathi.service.AccountService;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    private AccountService accountService;
    private PortalService portalService;

    @Override
    public void init() {
        this.accountService = new AccountService();
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        req.setAttribute("account", portalService.getAccountByUserId(user.getId()).orElse(null));
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/user/deposit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = ServletUtil.getLoggedInUser(req);
        try {
            Double amount = Double.parseDouble(req.getParameter("amount"));
            String pin = req.getParameter("transactionPin");
            accountService.deposit(user.getId(), amount, pin);
            ServletUtil.putFlash(req, "success", "Deposit completed successfully.");
        } catch (Exception e) {
            ServletUtil.putFlash(req, "error", resolveMessage(e));
        }
        resp.sendRedirect(req.getContextPath() + "/deposit");
    }

    private String resolveMessage(Exception e) {
        Throwable cause = e.getCause();
        return cause != null && cause.getMessage() != null ? cause.getMessage() : e.getMessage();
    }
}
