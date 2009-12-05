package com.sres.model;

import com.sres.persistence.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bbto
 */
public class User {
    private static final int ADMIN = 0;
    private static final int PROFESSOR = 1;
    private static final int STUDENT = 2;

    private int id;
    private String email;
    private String password;
    private String password_confirmation;
    private String firstname;
    private String lastname;
    private int role;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public static User find_by_id(String id) {
        User user = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if(db!=null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM users WHERE id=" + id);
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return user;
    }

    public void save() {

    }

    public void destroy() {

    }
}
