package servlets;

import db.Comments;
import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/editComment")
public class EditCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("com_id"));
        String comment = req.getParameter("recomment");
        String redirect = "/404.jsp";
        if(comment!=null) {
            Comments comment1 = DBManager.getCommentById(id);
            if(comment1!=null) {
                comment1.setComment(comment);
                if(DBManager.editComments(comment1)) {
                    redirect = "/home";
                }
            }
        }
        resp.sendRedirect(redirect);
    }
}
