package db;


import java.sql.Timestamp;

public class Comments {
    private Long id;
    private String comment;
    private Timestamp post_date;
    private News news_id;
    private Users users_id;

    public Comments(Long id, String comment, Timestamp post_date, News news_id, Users users_id) {
        this.id = id;
        this.comment = comment;
        this.post_date = post_date;
        this.news_id = news_id;
        this.users_id = users_id;
    }

    public Comments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public News getNews_id() {
        return news_id;
    }

    public void setNews_id(News news_id) {
        this.news_id = news_id;
    }

    public Users getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Users users_id) {
        this.users_id = users_id;
    }
}
