package com.banking.sathi.controller;

import com.banking.sathi.dto.request.CardRequestDto;
import com.banking.sathi.enums.CardType;
import com.banking.sathi.model.User;
import com.banking.sathi.service.CardService;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cards/apply")
public class CardApplicationServlet extends HttpServlet {
    private CardService cardService;
    private PortalService portalService;

    @Override
    public void init() {
        this.cardService = new CardService();
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        req.setAttribute("monthlyIncome", portalService.getMonthlyIncomeForUser(user.getId()));
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/user/apply-card.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = ServletUtil.getLoggedInUser(req);
        try {
            CardRequestDto dto = new CardRequestDto(
                    CardType.valueOf(req.getParameter("type").toUpperCase()),
                    Double.parseDouble(req.getParameter("creditLimit"))
            );
            cardService.saveCard(dto, user.getId());
            ServletUtil.putFlash(req, "success", "Card application submitted.");
        } catch (Exception e) {
            ServletUtil.putFlash(req, "error", resolveMessage(e));
        }
        resp.sendRedirect(req.getContextPath() + "/cards/apply");
    }

    private String resolveMessage(Exception e) {
        Throwable cause = e.getCause();
        return cause != null && cause.getMessage() != null ? cause.getMessage() : e.getMessage();
    }
}
