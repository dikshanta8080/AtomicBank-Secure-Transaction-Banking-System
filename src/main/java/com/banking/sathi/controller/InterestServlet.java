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
}
