package com.banking.sathi.controller;

import com.banking.sathi.enums.Role;
import com.banking.sathi.model.User;
import com.banking.sathi.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    //this handles get request when user opens login page//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("pages/login.jsp").forward(req, resp);}

    //this handles post req when user submits the login form//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//get email and pasword frm th form//
        String email = req.getParameter("email");
        String password = req.getParameter("password");

//create authservice obj to cal login method//
        AuthService authService = new AuthService();
        try{
//cal login method frm authservice//
            User obj = authService.login(email, password);

//store user in sesion//
            HttpSession session = req.getSession();
            session.setAttribute("user", obj);

//chck rol and redirct to corect dashboard//
            if (obj.getRole() == Role.ADMIN) {
                resp.sendRedirect("admin/dashboard");}
            else {resp.sendRedirect("user/dashboard");}}

        catch (Exception e){
//store eror message in request n frwrd bck to login pg//
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);}}}
