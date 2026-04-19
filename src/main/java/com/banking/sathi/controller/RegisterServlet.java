package com.banking.sathi.controller;

import com.banking.sathi.model.User;
import com.banking.sathi.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    //this handles get req when user opens regis pg//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("pages/register.jsp").forward(req, resp);}

    //this handles post req when user submits the regisr form//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//get form data frm th req//
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");

//checkimg if any field is emty//
        if(name == null || name.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty() ||
                address == null || address.isEmpty()){
            req.setAttribute("error", "please fill all the fields");
            req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
            return; }

//create user obj with the form data//
        User user = new User(name, password, email, address);

//create authservice obj to cal regis method//
        AuthService authService = new AuthService();

        try{
//call regisrUser method frm authservice//
            boolean isRegistered = authService.registerUser(user);

            if(isRegistered){

//storimg sucess msg in session n go to login page//
                HttpSession session = req.getSession();
                session.setAttribute("success", "account created successfully please log in");
                resp.sendRedirect("login");}
            else{

//something went wrong store eror in req n forward bck//
                req.setAttribute("error", "registration failed please try again");
                req.getRequestDispatcher("pages/register.jsp").forward(req, resp);}}

        catch(Exception e){
//store eror msg in req and forward back to regist pg//
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/register.jsp").forward(req, resp);}}}








