package db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/final_project?useUnicode=true&serverTimezone=UTC",
                    "root",
                    "");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("role_id")
                        );
            }
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static boolean addUser(Users user) {
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name) " +
                    "VALUES (?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullName());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT n.id, n.post_date, n.category_id, n.title, n.content,ne.name AS CategoryName FROM news AS n INNER JOIN news_categories ne ON n.category_id = ne.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("post_date"),
                        new News_Category(resultSet.getLong("category_id"),resultSet.getString("CategoryName")),
                        resultSet.getString("title"),
                        resultSet.getString("content")
                ));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static boolean editNews(News news) {
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET post_date = NOW(), category_id = ?, title = ?, content = ? WHERE id = ?");
            statement.setLong(1, news.getCategory_id().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.setLong(4,news.getId());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addNews(News news) {
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO news(post_date, category_id, title, content)" +
                    "VALUES (NOW(),?,?,?)");
            statement.setLong(1,news.getCategory_id().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
    public static News getNewsById(Long id) {
        News ns = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT n.id, n.post_date, n.category_id, n.title, n.content,ne.name AS CategoryName FROM news AS n INNER JOIN news_categories ne ON n.category_id = ne.id WHERE n.id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                ns = new News(resultSet.getLong("id"),
                            resultSet.getTimestamp("post_date"),
                            new News_Category(resultSet.getLong("id"), resultSet.getString("CategoryName")),
                            resultSet.getString("title"),
                            resultSet.getString("content"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ns;
    }
    public static ArrayList<News_Category> getNewsCategory() {
        ArrayList<News_Category> newsCategories = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news_categories");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
             newsCategories.add(
                     new News_Category(
                             resultSet.getLong("id"),
                             resultSet.getString("name")));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategories;
    }
    public static News_Category getNewsCategoryById(Long id) {
        News_Category newsCategory = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news_categories WHERE id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                newsCategory = new News_Category(resultSet.getLong("id"), resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategory;

    }
    public static boolean addComment(Comments comment){
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO comments(comment,user_id,news_id,post_date) VALUES (?,?,?,NOW())");
            statement.setString(1,comment.getComment());
            statement.setLong(2,comment.getUsers_id().getId());
            statement.setLong(3,comment.getNews_id().getId());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return rows > 0;
    }
    public static ArrayList<Comments> getAllComments(Long id) {
        ArrayList<Comments> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.comment, c.post_date, c.user_id, c.news_id, u.email, u.full_name  FROM comments c INNER JOIN users u ON c.user_id = u.id WHERE c.news_id = ? ORDER BY c.post_date DESC ");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comments comment = new Comments();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPost_date(resultSet.getTimestamp("post_date"));
                Users user = new Users();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUsers_id(user);
                News news = new News();
                news.setId(resultSet.getLong("news_id"));
                comment.setNews_id(news);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return comments;
    }
}
