package com.banking.sathi.controller;

import com.banking.sathi.service.PortalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// shows all failed transactions to the admin
@WebServlet("/admin/failed-operations")
public class AdminFailedOperationsServlet extends HttpServlet {
    private PortalService portalService;

    // init portal service on load
    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    // fetch and display failed transactions
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("failedTransactions", portalService.getFailedTransactions());
        req.getRequestDispatcher("/views/admin/failed-operations.jsp").forward(req, resp);
    }
}
