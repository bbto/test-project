package com.sres.model;

import com.sres.persistence.DatabaseManager;

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


    public static User find_by_id(String id) {
        User user = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if(db!=null) {
            
        }
        db.close();
        return user;
    }

    public void save() {

    }

    public void destroy() {

    }
}
