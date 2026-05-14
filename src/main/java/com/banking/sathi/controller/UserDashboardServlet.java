package com.banking.sathi.controller;

import com.banking.sathi.dto.response.TransactionViewDto;
import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.model.Account;
import com.banking.sathi.model.User;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserDashboard")
public class UserDashboardServlet extends HttpServlet {
    private PortalService portalService;

    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        Account account = portalService.getAccountByUserId(user.getId()).orElse(null);
        List<TransactionViewDto> transactions = portalService.getUserTransactions(user.getId(), null, null);
        List<TransactionViewDto> recentTransactions = new ArrayList<>(transactions.subList(0, Math.min(transactions.size(), 8)));

        req.setAttribute("account", account);
        req.setAttribute("transactions", recentTransactions);
        req.setAttribute("transactionCount", portalService.countTransactionsForUser(user.getId()));
        req.setAttribute("interestAmount", portalService.getInterestForUser(user.getId()));
        req.setAttribute("issuedCards", portalService.countCardsByStatusForUser(user.getId(), CardStatus.ISSUED));
        req.setAttribute("pendingCards", portalService.countCardsByStatusForUser(user.getId(), CardStatus.PENDING));
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/user/dashboard.jsp").forward(req, resp);
    }
}
