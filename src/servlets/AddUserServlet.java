package servlets;


import db.DBManager;
import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/register")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("user_email");
        String password = req.getParameter("user_password");
        String password2 = req.getParameter("user_repassword");
        String full_name = req.getParameter("user_full_name");
        String redirect = "/register?email_Error";
        Users us = DBManager.getUser(email);
        if(us==null) {
            redirect = "/register?password_Error";
            if(password.equals(password2)) {
                Users user = new Users();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(full_name);
                user.setRole_id(2);
                if(DBManager.addUser(user)) {
                    redirect = "/register?success";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
