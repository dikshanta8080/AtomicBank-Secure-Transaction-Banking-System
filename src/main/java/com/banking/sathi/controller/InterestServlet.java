package com.banking.sathi.controller;

import com.banking.sathi.exceptions.AccountDoesNotExistsException;
import com.banking.sathi.service.InterestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InterestServlet", value = "/interest/*")
public class InterestServlet extends HttpServlet {
    private InterestService interestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.interestService = new InterestService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Account id is required");
            return;
        }
        try {
            Long accountId = Long.parseLong(pathInfo.substring(1));
            double interest = interestService.calculateSimpleInterest(accountId);
            resp.setContentType("text/plain");
            resp.getWriter().write(String.valueOf(interest));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account id");
        } catch (AccountDoesNotExistsException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            com.banking.sathi.model.User user = com.banking.sathi.utils.ServletUtil.getLoggedInUser(req);
            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            com.banking.sathi.service.PortalService portalService = new com.banking.sathi.service.PortalService();
            com.banking.sathi.model.Account account = portalService.getAccountByUserId(user.getId())
                    .orElseThrow(() -> new AccountDoesNotExistsException("Account not found"));
            interestService.applyInterest(account.getId());
            com.banking.sathi.utils.ServletUtil.putFlash(req, "success", "Interest applied successfully");
            resp.sendRedirect(req.getContextPath() + "/interest-summary");
        } catch (AccountDoesNotExistsException e) {
            com.banking.sathi.utils.ServletUtil.putFlash(req, "error",
                    e.getMessage() != null ? e.getMessage() : "Account not found");
            resp.sendRedirect(req.getContextPath() + "/interest-summary");
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage()
                    : (e.getCause() != null && e.getCause().getMessage() != null
                       ? e.getCause().getMessage() : "An unexpected error occurred");
            com.banking.sathi.utils.ServletUtil.putFlash(req, "error", msg);
            resp.sendRedirect(req.getContextPath() + "/interest-summary");
        }
    }
}
