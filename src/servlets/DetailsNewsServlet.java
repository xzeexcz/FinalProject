package servlets;

import db.Comments;
import db.DBManager;
import db.News;
import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = -1L;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
            News ss = DBManager.getNewsById(id);
            if (ss != null) {
                ArrayList<Comments> comments = DBManager.getAllComments(ss.getId());
                req.setAttribute("com",comments);
                req.setAttribute("nv", ss);
                req.getRequestDispatcher("/details.jsp").forward(req, resp);
            }
    }
}
