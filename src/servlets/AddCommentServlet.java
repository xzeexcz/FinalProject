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

@WebServlet(value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = "/login";
        req.setCharacterEncoding("utf-8");
        Users user = (Users) req.getSession().getAttribute("CURRENT_USER");
        if(user!=null) {
            String comment = req.getParameter("comment");
            Long id = -1L;
            try{
                id = Long.parseLong(req.getParameter("news_id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            News news = DBManager.getNewsById(id);
            if(news!=null){
                Comments comments = new Comments();
                comments.setComment(comment);
                comments.setNews_id(news);
                comments.setUsers_id(user);
                if(DBManager.addComment(comments)){
                    redirect = "/details?id="+id;
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
