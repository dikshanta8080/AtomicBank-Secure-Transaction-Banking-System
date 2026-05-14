package com.banking.sathi.controller;

import com.banking.sathi.service.AccountService;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminDashboardServlet", value = "/AdminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    private AccountService accountService;
    private PortalService portalService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accountService = new AccountService();
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pendingAccounts", accountService.getPendingApprovalAccounts());
        req.setAttribute("pendingCards", portalService.getPendingCards());
        req.setAttribute("totalAccounts", portalService.getTotalAccounts());
        req.setAttribute("pendingApprovals", portalService.getPendingAccountsCount());
        req.setAttribute("totalDeposits", portalService.getTotalDeposits());
        req.setAttribute("totalCards", portalService.getTotalCards());
        req.setAttribute("pendingCardCount", portalService.getPendingCardsCount());
        req.setAttribute("rolledBackCount", portalService.countTransactionsByStatus(com.banking.sathi.enums.TransactionStatus.ROLLED_BACK));
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
