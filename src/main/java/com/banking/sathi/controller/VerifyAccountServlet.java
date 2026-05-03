package com.banking.sathi.controller;

import com.banking.sathi.service.AccountService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/verify-account")
public class VerifyAccountServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Long userId = Long.parseLong(req.getParameter("userId"));

        accountService.verifyAccount(userId);

        resp.sendRedirect(req.getContextPath() + "/admin/pending-accounts");
    }
}