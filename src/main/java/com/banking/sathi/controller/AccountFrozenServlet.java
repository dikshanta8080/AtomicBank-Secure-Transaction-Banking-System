package com.banking.sathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// shown when a user's account is frozen
@WebServlet("/AccountFrozen")
public class AccountFrozenServlet extends HttpServlet {
    
     // just forwards to the frozen account page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/account-frozen.jsp").forward(req, resp);
    }
}
