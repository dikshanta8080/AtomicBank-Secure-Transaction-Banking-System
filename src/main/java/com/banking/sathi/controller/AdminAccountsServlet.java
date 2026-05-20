package com.banking.sathi.controller;

import com.banking.sathi.service.AccountService;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// admin servlet for managing user accounts
@WebServlet("/admin/accounts")
public class AdminAccountsServlet extends HttpServlet {
    private PortalService portalService;
    private AccountService accountService;


    // spin up services on load
    @Override
    public void init() {
        this.portalService = new PortalService();
        this.accountService = new AccountService();
    }

    // load accounts page with pending + all accounts
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pendingAccounts", accountService.getPendingApprovalAccounts());
        req.setAttribute("allAccounts", portalService.getAllAccountsForAdmin());
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/admin/accounts.jsp").forward(req, resp);
    }

     // handle approve / freeze / reject actions
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        Long userId = Long.parseLong(req.getParameter("userId"));
        try {
            if ("approve".equals(action)) {
                accountService.verifyAccount(userId);
                ServletUtil.putFlash(req, "success", "Account approved successfully.");
            } else if ("freeze".equals(action)) {
                accountService.freezeAccountByUserId(userId);
                ServletUtil.putFlash(req, "success", "Account frozen successfully.");
            } else if ("reject".equals(action)) {
                accountService.deleteAccountByUserId(userId);
                ServletUtil.putFlash(req, "success", "Account request rejected and removed.");
            }
        } catch (Exception e) {
            ServletUtil.putFlash(req, "error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/admin/accounts");
    }
}
