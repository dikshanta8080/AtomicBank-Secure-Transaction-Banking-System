package com.banking.sathi.controller;

import com.banking.sathi.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/accounts/review")
public class AdminAccountReviewServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init() {
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        req.setAttribute("detail", accountService.getPendingApprovalAccountDetails(userId));
        req.getRequestDispatcher("/views/admin/account-review.jsp").forward(req, resp);
    }
}
