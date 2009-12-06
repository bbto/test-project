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
public class Competition {

    private int id;
    private String name;
    private boolean newRecord = false;

    public Competition(boolean newRecord){
        this.newRecord=newRecord;
        id=0;
        name=null;
    }

    public static Competition find_by_id(String id) {
        Competition competition = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM competitions WHERE id=" + id);
                if (rs.next()) {
                    competition = new Competition(false);
                    competition.setId(rs.getInt("id"));
                    competition.setName(rs.getString("name"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return competition;
    }

    public static ArrayList<Competition> all() {
        ArrayList<Competition> result = new ArrayList<Competition>();
        DatabaseManager db = DatabaseManager.getInstance();
        Competition competition = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM competitions");
                while (rs.next()) {
                    competition = new Competition(false);
                    competition.setId(rs.getInt("id"));
                    competition.setName(rs.getString("name"));
                    result.add(competition);
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
                    if (db.insert("competitions", "(name)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("name=" + Util.quote(name));
                    if (db.update("competitions", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("competitions", "id=" + id)) {
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
