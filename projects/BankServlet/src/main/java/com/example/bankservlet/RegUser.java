package com.example.bankservlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/UserReg")
public class RegUser extends HttpServlet {
    static ArrayList<String> listUsers = new ArrayList<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        User userInfo = new User(user,pass);
        listUsers.add("admin admin 10000");
        listUsers.add("vlad 12345 34000");
        listUsers.add(userInfo.toString());
        System.out.println(listUsers);
        response.sendRedirect("/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        User userInfo = new User(user,pass);
        listUsers.add(userInfo.toString());
        System.out.println(listUsers);

    }

}
