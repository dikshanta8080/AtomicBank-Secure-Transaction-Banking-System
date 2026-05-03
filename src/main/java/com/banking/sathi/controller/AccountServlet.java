package com.banking.sathi.controller;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.exceptions.AccountCreationFailedException;
import com.banking.sathi.model.User;
import com.banking.sathi.service.AccountService;
import com.banking.sathi.validators.AccountValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AccountServlet", value = "/account")
public class AccountServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.getRequestDispatcher("/views/account/account.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        try {

            AccountType accountType =
                    AccountType.valueOf(req.getParameter("accountType").toUpperCase());

            AccountCreationRequest request = new AccountCreationRequest(
                    accountType,
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

            AccountCreationResponseDto responseDto =
                    accountService.createAccount(request, user.getId());
            if (responseDto != null) {
                session.setAttribute("accountResponse", responseDto);

                resp.sendRedirect(req.getContextPath() + "/PendingApproval");
            }


        } catch (IllegalArgumentException | AccountCreationFailedException e) {
            // ya logger halna baki xa sabbai exception ma
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/views/account/account.jsp").forward(req, resp);

        } catch (Exception e) {
            // ya logger halna baki xa sabbai exception ma
            req.setAttribute("error", "Unexpected error occurred");
            req.getRequestDispatcher("/views/account/account.jsp").forward(req, resp);
        }
    }
}