package servlets;

import db.DBManager;
import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("user_email");
        String password = req.getParameter("user_password");

        String redirect = "/login?email_error";

        Users user = DBManager.getUser(email);
        if(user!=null) {
            redirect = "/login?password_error";
            if(user.getPassword().equals(password)) {
                req.getSession().setAttribute("CURRENT_USER",user);
                redirect = "/home";
            }
        }
        resp.sendRedirect(redirect);
    }
}
