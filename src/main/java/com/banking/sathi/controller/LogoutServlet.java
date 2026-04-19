package com.banking.sathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
// kushal changed this
public class LogoutServlet extends HttpServlet {
    //this handles get req when user clicks lgout//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//get th current sesion//
        HttpSession session = req.getSession(false);
        if (session != null) {

//destroying th sesion user data is removed//
            session.invalidate();}

//send user bck to login pg//
        resp.sendRedirect("login");}}
