package com.sres.model;

import com.sres.persistence.DatabaseManager;
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



    public static Competition find_by_id(String id) {
        Competition competition = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if(db!=null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM competitions WHERE id=" + id);
                if (rs.next()) {
                    competition = new Competition();
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
        ArrayList<Competition> result = new ArrayList();
        DatabaseManager db = DatabaseManager.getInstance();
        Competition competition = null;
        if(db!=null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM competitions");
                while (rs.next()) {
                    competition = new Competition();
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

    public void save() {

    }

    public void destroy() {

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
