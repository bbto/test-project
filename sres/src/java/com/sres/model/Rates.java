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
public class Rates {

    private int id;
    private int student_subject_id;
    private double cuantification;
    private String qualification;
    private int activity_id;
    private String answer;
    private int type;
    private String link;
    private int scribd_id;
    private String scribd_key;
    private boolean newRecord = false;

    public Rates() {
        newRecord = true;
        id = 0;
        student_subject_id = 0;
        cuantification = 0.0;
        qualification = null;
        activity_id = 0;
        answer = null;
        type = 0;
        link = null;
        scribd_id = 0;
        scribd_key = null;
    }

    public static Rates find_by_id(String id) {
        Rates rate = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM rates WHERE id=" + id);
                if (rs.next()) {
                    rate = new Rates();
                    rate.setId(rs.getInt("id"));
                    rate.setName(rs.getString("name"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return rate;
    }

    public static ArrayList<Rates> all() {
        ArrayList<Rates> result = new ArrayList<Rates>();
        DatabaseManager db = DatabaseManager.getInstance();
        Rates rate = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM rates");
                while (rs.next()) {
                    rate = new Rates();
                    rate.setId(rs.getInt("id"));
                    rate.setName(rs.getString("name"));
                    result.add(rate);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();

        return result;
    }

    public boolean save() {
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            if (validate()) {
                if (newRecord) {
                    ArrayList fields = new ArrayList();
                    fields.add(Util.quote(name));
                    if (db.insert("rates", "(name)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("name=" + Util.quote(name));
                    if (db.update("rates", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("rates", "id=" + id)) {
                    return true;
                }
            }
        }
        db.close();
        return false;
    }

    private boolean validate() {
        return true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


}
