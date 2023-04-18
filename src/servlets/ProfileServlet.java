package servlets;

import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users us = (Users) req.getSession().getAttribute("CURRENT_USER");
        if (us != null) {
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
