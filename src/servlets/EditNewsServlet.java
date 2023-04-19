package servlets;

import db.DBManager;
import db.News;
import db.News_Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/editNews")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = -1L;
        String title = req.getParameter("re_title");
        String content = req.getParameter("re_content");
        String redirect = "/404";
        try{
            id = Long.parseLong(req.getParameter("news_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        News news = DBManager.getNewsById(id);
        if(news!=null) {
            news.setTitle(title);
            news.setContent(content);
            if(DBManager.editNews(news)) {
                redirect = "/home";
            }
        }
        resp.sendRedirect(redirect);
    }
}
