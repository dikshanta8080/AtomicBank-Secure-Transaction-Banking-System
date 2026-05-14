package com.banking.sathi.controller;

import com.banking.sathi.model.User;
import com.banking.sathi.service.PortalService;
import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cards")
public class MyCardsServlet extends HttpServlet {
    private PortalService portalService;

    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        req.setAttribute("cards", portalService.getCardsForUser(user.getId()));
        req.getRequestDispatcher("/views/user/cards.jsp").forward(req, resp);
    }
}
