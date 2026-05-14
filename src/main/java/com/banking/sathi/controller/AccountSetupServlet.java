package com.banking.sathi.controller;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.model.User;
import com.banking.sathi.service.AccountService;
import com.banking.sathi.utils.ServletUtil;
import com.banking.sathi.validators.AccountValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/account")
public class AccountSetupServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init() {
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("success", ServletUtil.consumeFlash(req, "success"));
        req.setAttribute("error", ServletUtil.consumeFlash(req, "error"));
        req.getRequestDispatcher("/views/user/create-account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtil.getLoggedInUser(req);

        try {
            AccountCreationRequest request = new AccountCreationRequest(
                    AccountType.valueOf(req.getParameter("accountType").toUpperCase()),
                    LocalDate.parse(req.getParameter("dob")),
                    req.getParameter("gender"),
                    req.getParameter("citizenship"),
                    LocalDate.parse(req.getParameter("citizenshipIssueDate")),
                    req.getParameter("citizenshipDistrict"),
                    req.getParameter("phone"),
                    req.getParameter("occupation"),
                    Double.parseDouble(req.getParameter("income")),
                    req.getParameter("province"),
                    req.getParameter("district"),
                    req.getParameter("city"),
                    Integer.parseInt(req.getParameter("ward")),
                    req.getParameter("tole"),
                    req.getParameter("fatherName"),
                    req.getParameter("motherName")
            );

            AccountValidator.validateAccountCredentials(request);
            AccountCreationResponseDto responseDto = accountService.createAccount(request, user.getId());
            ServletUtil.putFlash(req, "accountNumber", responseDto.getAccountNumber());
            ServletUtil.putFlash(req, "transactionPin", responseDto.getTransactionPin());
            resp.sendRedirect(req.getContextPath() + "/PendingApproval");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/views/user/create-account.jsp").forward(req, resp);
        }
    }
}
