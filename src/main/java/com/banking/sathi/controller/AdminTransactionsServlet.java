package com.banking.sathi.controller;

import com.banking.sathi.service.PortalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// admin view for all transactions across the system
@WebServlet("/admin/transactions")
public class AdminTransactionsServlet extends HttpServlet {
    private PortalService portalService;

    // init portal service on startup
    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    // fetch all transactions and forward to the view
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("transactions", portalService.getAllTransactions());
        req.getRequestDispatcher("/views/admin/transactions.jsp").forward(req, resp);
    }
}
