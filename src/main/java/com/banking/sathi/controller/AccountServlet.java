package com.banking.sathi.controller;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.exceptions.*;
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
        AccountService accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        try {
            AccountType accountType = AccountType.valueOf(req.getParameter("accountType").toUpperCase());

            LocalDate dob = LocalDate.parse(req.getParameter("dob"));
            String gender = req.getParameter("gender");
            String citizenship = req.getParameter("citizenship");
            LocalDate citizenshipIssueDate = LocalDate.parse(req.getParameter("citizenshipIssueDate"));
            String citizenshipDistrict = req.getParameter("citizenshipDistrict");
            String phone = req.getParameter("phone");
            String occupation = req.getParameter("occupation");
            Double income = Double.parseDouble(req.getParameter("income"));

            String province = req.getParameter("province");
            String district = req.getParameter("district");
            String city = req.getParameter("city");
            Integer ward = Integer.parseInt(req.getParameter("ward"));
            String tole = req.getParameter("tole");

            String fatherName = req.getParameter("fatherName");
            String motherName = req.getParameter("motherName");

            AccountCreationRequest request = new AccountCreationRequest(
                    accountType, dob, gender, citizenship, citizenshipIssueDate,
                    citizenshipDistrict, phone, occupation, income,
                    province, district, city, ward, tole,
                    fatherName, motherName
            );

            AccountValidator.validateAccountCredentials(request);

            AccountCreationResponseDto responseDto =
                    accountService.createAccount(request, user.getId());

            session.setAttribute("accountResponse", responseDto);

            resp.sendRedirect("account?success=true");

        } catch (AccountAlreadyExistsException |
                 KycAlreadyExistsException |
                 UnauthorizedAccessException |
                 UserDoesnotExistsException e) {

            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("views/account.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "Invalid input data");
            req.getRequestDispatcher("views/account.jsp").forward(req, resp);
        } catch (AccountCreationFailedException e) {
            req.setAttribute("error", "Something went wrong. Please try again.");
            req.getRequestDispatcher("views/account.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Unexpected error occurred");
            req.getRequestDispatcher("views/account.jsp").forward(req, resp);
        }
    }
}