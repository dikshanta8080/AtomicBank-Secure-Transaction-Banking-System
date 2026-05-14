package com.banking.sathi.controller;

import com.banking.sathi.service.PortalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/failed-operations")
public class AdminFailedOperationsServlet extends HttpServlet {
    private PortalService portalService;

    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("failedTransactions", portalService.getFailedTransactions());
        req.getRequestDispatcher("/views/admin/failed-operations.jsp").forward(req, resp);
    }
}
