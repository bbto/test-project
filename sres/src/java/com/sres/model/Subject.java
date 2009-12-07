package com.sres.model;

import com.sres.persistence.DatabaseManager;
import com.sres.util.Util;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bbto
 */
public class Subject {

    private int id;
    private int competence_id;
    private int professor_id;
    private Date creation_date;
    private boolean newRecord = false;

    public Subject(boolean newRecord) {
        this.newRecord = newRecord;
        id = 0;
        competence_id = 0;
        professor_id = 0;
        creation_date = null;
    }

    public static Subject find_by_id(String id) {
        Subject competition = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM subjects WHERE id=" + id);
                if (rs.next()) {
                    competition = new Subject(false);
                    competition.setId(rs.getInt("id"));
                    competition.setCompetence_id(rs.getInt("competence_id"));
                    competition.setProfessor_id(rs.getInt("professor_id"));
                    competition.setCreation_date(rs.getDate("creation_date"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return competition;
    }

    public ArrayList<Activity> getActivities() {
        
        ArrayList<Activity> result = new ArrayList<Activity>();
        DatabaseManager db = DatabaseManager.getInstance();
        Activity competition = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM activities s WHERE  s.subject_id=" + id);
                while (rs.next()) {
                    competition = new Activity();
                    competition.setId(rs.getInt("id"));
                    competition.setSubject_id(rs.getInt("subject_id"));
                    competition.setType(rs.getInt("type"));
                    competition.setLink(rs.getString("link"));
                    competition.setScrib_id(rs.getInt("scribd_id"));
                    competition.setScrib_key(rs.getString("scribd_key"));
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

    public static ArrayList<Subject> all() {
        ArrayList<Subject> result = new ArrayList<Subject>();
        DatabaseManager db = DatabaseManager.getInstance();
        Subject competition = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM subjects");
                while (rs.next()) {
                    competition = new Subject(false);
                    competition.setId(rs.getInt("id"));
                    competition.setCompetence_id(rs.getInt("competition_id"));
                    competition.setProfessor_id(rs.getInt("professor_id"));
                    competition.setCreation_date(rs.getDate("creation_date"));
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
                    fields.add(String.valueOf(competence_id));
                    fields.add(String.valueOf(professor_id));
                    fields.add(Util.quote(creation_date.toString()));
                    if (db.insert("subjects", "(competition_id,professor_id,creation_date)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("competence_id=" + String.valueOf(competence_id));
                    fields.add("professor_id=" + String.valueOf(professor_id));
                    fields.add("creation_date=" + Util.quote(creation_date.toString()));
                    if (db.update("subjects", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("subjects", "id=" + id)) {
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
     * @return the competence_id
     */
    public int getCompetence_id() {
        return competence_id;
    }

    /**
     * @param competence_id the competence_id to set
     */
    public void setCompetence_id(int competence_id) {
        this.competence_id = competence_id;
    }

    /**
     * @return the professor_id
     */
    public int getProfessor_id() {
        return professor_id;
    }

    /**
     * @param professor_id the professor_id to set
     */
    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    /**
     * @return the creation_date
     */
    public Date getCreation_date() {
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
