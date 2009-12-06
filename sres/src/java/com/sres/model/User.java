package com.sres.model;

import com.sres.persistence.DatabaseManager;
import com.sres.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private boolean newRecord = false;

    public User() {
        newRecord = true;
        id = 0;
        email = null;
        password = null;
        password_confirmation = null;
        firstname = null;
        lastname = null;
        role = 0;
    }

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
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT password(" + Util.quote(password) + ") FROM dual");
                if (rs.next()) {
                    this.password = rs.getString(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT password(" + Util.quote(password_confirmation) + ") FROM dual");
                if (rs.next()) {
                    this.password_confirmation = rs.getString(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
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

    public static ArrayList<User> all() {
        ArrayList<User> users = new ArrayList<User>();
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM users");
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                    user.newRecord = false;
                    users.add(user);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return users;
    }

    public static User find_by_id(String id) {
        User user = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM users WHERE id=" + id);
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                    user.newRecord = false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return user;
    }

    public static User find_by_email(String email) {
        User user = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM users WHERE email=" + Util.quote(email));
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                    user.newRecord = false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return user;
    }

    public static User find_by_firstname(String firstname) {
        User user = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM users WHERE firstname like " + Util.has(firstname));
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                    user.newRecord = false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return user;
    }

    private boolean validate() {
        return true;
    }

    public boolean save() {
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            if(validate()) {
                if (newRecord) {
                    ArrayList fields = new ArrayList();
                    fields.add(Util.quote(email));
                    fields.add(Util.quote(password));
                    fields.add(Util.quote(firstname));
                    fields.add(Util.quote(lastname));
                    fields.add("" + role);
                    if (db.insert("users", "(email,password,firstname,lastname,role)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("email=" + Util.quote(email));
                    fields.add("firstname=" + Util.quote(firstname));
                    fields.add("lastname=" + Util.quote(lastname));
                    fields.add("role=" + role);
                    if (db.update("users", Util.concat(fields, ","), "id=" + id)) {
                        return true;
                    }
                }
            }
        }
        db.close();
        return false;
    }

    public boolean destroy() {
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            if (!newRecord) {
                if (db.destroy("users", "id=" + id)) {
                    return true;
                }
            }
        }
        db.close();
        return false;
    }
}
