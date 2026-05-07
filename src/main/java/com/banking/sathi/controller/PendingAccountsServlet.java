package com.banking.sathi.controller;

import com.banking.sathi.dto.response.AccountListDTO;
import com.banking.sathi.service.AccountService;
import com.banking.sathi.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/pending-accounts")
public class PendingAccountsServlet extends HttpServlet {

    private UserService userService;
    private AccountService accountService;

    @Override
    public void init() {
        userService = new UserService();
        accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<AccountListDTO> accounts = accountService.getPendingApprovalAccounts();

        req.setAttribute("accounts", accounts);

        req.getRequestDispatcher("/views/account/pendingAccounts.jsp")
                .forward(req, resp);
    }
}
