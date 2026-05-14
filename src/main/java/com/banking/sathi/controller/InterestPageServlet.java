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

@WebServlet("/interest-summary")
public class InterestPageServlet extends HttpServlet {
    private PortalService portalService;

    @Override
    public void init() {
        this.portalService = new PortalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);
        req.setAttribute("account", portalService.getAccountByUserId(user.getId()).orElse(null));
        req.setAttribute("interestAmount", portalService.getInterestForUser(user.getId()));
        req.setAttribute("interestRate", 5.0);
        req.setAttribute("success", com.banking.sathi.utils.ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", com.banking.sathi.utils.ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/user/interest.jsp").forward(req, resp);
    }
}
