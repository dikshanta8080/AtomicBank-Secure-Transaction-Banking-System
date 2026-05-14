package com.banking.sathi.controller;

import com.banking.sathi.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/PendingApproval")
public class PendingApprovalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accountNumber", ServletUtil.consumeFlash(req, "accountNumber"));
        req.setAttribute("transactionPin", ServletUtil.consumeFlash(req, "transactionPin"));
        req.getRequestDispatcher("/views/user/pending-approval.jsp").forward(req, resp);
    }
}
