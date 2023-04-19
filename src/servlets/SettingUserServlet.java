package servlets;


import db.DBManager;
import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/settings")
public class SettingUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("CURRENT_USER") != null) {
            req.getRequestDispatcher("/settings.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/404");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String newName = req.getParameter("new_name");
        String password = req.getParameter("new_password");
        String password2 = req.getParameter("new_password2");
        String redirect = "/settings?password_error";
        Users us = DBManager.getUser(email);
        if(us!=null) {
            if(password2.equals(password)) {
                us.setFullName(newName);
                us.setPassword(password);
                if(DBManager.editUser(us)) {
                    req.getSession().removeAttribute("CURRENT_USER");
                    redirect = "/login";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
