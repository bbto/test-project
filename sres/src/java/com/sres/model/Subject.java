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
        Subject subject = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM subjects WHERE id=" + id);
                if (rs.next()) {

                    subject = new Subject(false);
                    subject.setId(rs.getInt("id"));
                    subject.setCompetence_id(rs.getInt("competition_id"));
                    subject.setProfessor_id(rs.getInt("professor_id"));
                    subject.setCreation_date(rs.getDate("creation_date"));

                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return subject;
    }

    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> result = new ArrayList<Activity>();
        DatabaseManager db = DatabaseManager.getInstance();
        Activity activity = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM activities s WHERE  s.subject_id=" + id);
                while (rs.next()) {
                    activity = new Activity(false);
                    activity.setId(rs.getInt("id"));
                    activity.setSubject_id(rs.getInt("subject_id"));
                    activity.setType(rs.getInt("type"));
                    activity.setLink(rs.getString("link"));
                    activity.setScrib_id(rs.getInt("scribd_id"));
                    activity.setScrib_key(rs.getString("scribd_key"));
                    activity.setName(rs.getString("name"));
                    result.add(activity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return result;
    }

    public ArrayList<User> getStudents() {
        ArrayList<User> result = new ArrayList<User>();
        DatabaseManager db = DatabaseManager.getInstance();
        User user = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT u.* FROM users u, student_subjects s WHERE  u.id=s.student_id AND s.subject_id=" + id);
                while (rs.next()) {
                    user = new User(false);
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setRole(rs.getInt("role"));
                    result.add(user);
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
        Subject subject = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM subjects");
                while (rs.next()) {

                    subject = new Subject(false);
                    subject.setId(rs.getInt("id"));
                    subject.setCompetence_id(rs.getInt("competition_id"));
                    subject.setProfessor_id(rs.getInt("professor_id"));
                    subject.setCreation_date(rs.getDate("creation_date"));
                    result.add(subject);
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

    public String getCompetenceName() {
        String name = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT name FROM competitions WHERE id=" + competence_id);
                if (rs.next()) {
                    name = rs.getString(1);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return name;
    }
}
