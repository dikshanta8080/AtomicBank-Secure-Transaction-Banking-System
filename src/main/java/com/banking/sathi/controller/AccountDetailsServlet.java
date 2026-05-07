package com.banking.sathi.controller;

import com.banking.sathi.dto.response.AccountDetailDTO;
import com.banking.sathi.service.AccountService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/approval-details")
public class AccountDetailsServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String context = req.getContextPath();
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(context + "/login");
            return;
        }

        String userIdParam = req.getParameter("userId");
        if (userIdParam == null) {
            resp.sendRedirect(context + "/admin/pending-accounts");
            return;
        }

        Long userId = Long.parseLong(userIdParam);
        AccountDetailDTO details = accountService.getPendingApprovalAccountDetails(userId);

        req.setAttribute("accountDetail", details);
        req.getRequestDispatcher("/views/account/ApprovalDetails.jsp")
                .forward(req, resp);
    }
}

