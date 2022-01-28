package com.example.demo2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/auth")
public class AuthUser extends HttpServlet {
    ArrayList<String> listUsers = RegUser.listUsers;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        User userInfo = new User(user, pass);
        String uL = userInfo.toString();
        boolean result = listUsers.stream().anyMatch(lang -> lang.equals(uL));
        System.out.println(result);
        if (result == true){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/User.jsp");
            dispatcher.forward(req,resp);
        }
        else if (result == false){
            resp.sendRedirect("/Wrong.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
    }
}
