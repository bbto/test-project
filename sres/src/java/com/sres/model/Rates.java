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
                    rate.setStudent_subject_id(rs.getInt("student_subject_id"));
                    rate.setCuantification(rs.getDouble("cuantification"));
                    rate.setQualification(rs.getString("qualification"));
                    rate.setActivity_id(rs.getInt("activity_id"));
                    rate.setAnswer(rs.getString("answer"));
                    rate.setType(rs.getInt("type"));
                    rate.setLink(rs.getString("link"));
                    rate.setScribd_id(rs.getInt("scribd_id"));
                    rate.setScribd_key(rs.getString("scribd_key"));
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
                    rate.setStudent_subject_id(rs.getInt("student_subject_id"));
                    rate.setCuantification(rs.getDouble("cuantification"));
                    rate.setQualification(rs.getString("qualification"));
                    rate.setActivity_id(rs.getInt("activity_id"));
                    rate.setAnswer(rs.getString("answer"));
                    rate.setType(rs.getInt("type"));
                    rate.setLink(rs.getString("link"));
                    rate.setScribd_id(rs.getInt("scribd_id"));
                    rate.setScribd_key(rs.getString("scribd_key"));
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
                if (isNewRecord()) {
                    ArrayList fields = new ArrayList();
                    fields.add(String.valueOf(student_subject_id));
                    fields.add(String.valueOf(cuantification));
                    fields.add(Util.quote(qualification));
                    fields.add(String.valueOf(activity_id));
                    fields.add(String.valueOf(type));
                    fields.add(Util.quote(link));
                    fields.add(String.valueOf(scribd_id));
                    fields.add(Util.quote(scribd_key));
                    if (db.insert("rates", "(student_subject_id,cuantification,qualification,activity_id,type,link,scribd_id,scribd_key)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("student_subject_id=" + String.valueOf(student_subject_id));
                    fields.add("cuantification=" + String.valueOf(cuantification));
                    fields.add("qualification=" + Util.quote(qualification));
                    fields.add("activity_id=" + String.valueOf(activity_id));
                    fields.add("type="+String.valueOf(type));
                    fields.add("link="+Util.quote(link));
                    fields.add("scribd_id="+String.valueOf(scribd_id));
                    fields.add("scribd_key="+Util.quote(scribd_key));
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
            if (!isNewRecord()) {
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

    /**
     * @return the student_subject_id
     */
    public int getStudent_subject_id() {
        return student_subject_id;
    }

    /**
     * @param student_subject_id the student_subject_id to set
     */
    public void setStudent_subject_id(int student_subject_id) {
        this.student_subject_id = student_subject_id;
    }

    /**
     * @return the cuantification
     */
    public double getCuantification() {
        return cuantification;
    }

    /**
     * @param cuantification the cuantification to set
     */
    public void setCuantification(double cuantification) {
        this.cuantification = cuantification;
    }

    /**
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return the activity_id
     */
    public int getActivity_id() {
        return activity_id;
    }

    /**
     * @param activity_id the activity_id to set
     */
    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the scribd_id
     */
    public int getScribd_id() {
        return scribd_id;
    }

    /**
     * @param scribd_id the scribd_id to set
     */
    public void setScribd_id(int scribd_id) {
        this.scribd_id = scribd_id;
    }

    /**
     * @return the scribd_key
     */
    public String getScribd_key() {
        return scribd_key;
    }

    /**
     * @param scribd_key the scribd_key to set
     */
    public void setScribd_key(String scribd_key) {
        this.scribd_key = scribd_key;
    }

    /**
     * @return the newRecord
     */
    public boolean isNewRecord() {
        return newRecord;
    }

    /**
     * @param newRecord the newRecord to set
     */
    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
