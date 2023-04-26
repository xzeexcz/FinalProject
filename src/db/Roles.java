package db;

public class Roles {
    private int id;
    private String role;

    public Roles(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
