package com.banking.sathi.controller;

import com.banking.sathi.service.CardService;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

// handles admin side card application approvals
@WebServlet("/admin/cards")
public class AdminCardApplicationsServlet extends HttpServlet {
    private PortalService portalService;
    private CardService cardService;

    // init both services on startup
    @Override
    public void init() {
        this.portalService = new PortalService();
        this.cardService = new CardService();
    }

    // load pending card applications into the view
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pendingCards", portalService.getPendingCards());
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/admin/card-applications.jsp").forward(req, resp);
    }

    // process issue or reject action on a card application
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        Long cardId = Long.parseLong(req.getParameter("cardId"));

        try {
            if ("issue".equals(action)) {
                Double creditLimit = Double.parseDouble(req.getParameter("creditLimit"));
                LocalDate expiryDate = LocalDate.parse(req.getParameter("expiryDate"));
                cardService.issueCard(cardId, creditLimit, expiryDate);
                ServletUtil.putFlash(req, "success", "Card issued successfully.");
            } else if ("reject".equals(action)) {
                cardService.rejectCard(cardId);
                ServletUtil.putFlash(req, "success", "Card application rejected.");
            }
        } catch (Exception e) {
            ServletUtil.putFlash(req, "error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/admin/cards");
    }
}
