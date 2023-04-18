package servlets;


import db.DBManager;
import db.News;
import db.News_Category;
import db.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/addNews")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users currentUser = (Users) req.getSession().getAttribute("CURRENT_USER");
        if(currentUser.getRole_id()== 1) {
        req.getRequestDispatcher("/addNews.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long cat_id = -1L;
        String redirect = "/addNews?error";
        try{
            cat_id = Long.parseLong(req.getParameter("category"));
        } catch (Exception e) {
            resp.sendRedirect(redirect);
        }
        News_Category ns = DBManager.getNewsCategoryById(cat_id);
        if(ns!=null) {
            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setCategory_id(ns);
            if(DBManager.addNews(news)) {
                redirect = "/addNews?success";
            }
        }
        resp.sendRedirect(redirect);
    }
}
