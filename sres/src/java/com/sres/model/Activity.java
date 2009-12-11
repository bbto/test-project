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
public class Activity {

    private int id;
    private int subject_id;
    private int type;
    private String link;
    private int scrib_id;
    private String scrib_key;
    private String name;
    private String description;


    private boolean newRecord = false;

    public Activity(boolean newRecord) {
        this.newRecord = newRecord;
        id = 0;
        subject_id = 0;
        type = 0;
        link = null;
        scrib_id = 0;
        scrib_key = null;
        name = null;
        description = null;
    }

    public static Activity find_by_id(String id) {
        Activity activity = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM activities WHERE id=" + id);
                if (rs.next()) {
                    activity = new Activity(false);
                    activity.setId(rs.getInt("id"));
                    activity.setSubject_id(rs.getInt("subject_id"));
                    activity.setType(rs.getInt("type"));
                    activity.setLink(rs.getString("link"));
                    activity.setScrib_id(rs.getInt("scribd_id"));
                    activity.setScrib_key(rs.getString("scribd_key"));
                    activity.setName(rs.getString("name"));
                    activity.setDescription(rs.getString("description"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return activity;
    }

    public static ArrayList<Activity> all() {
        ArrayList<Activity> result = new ArrayList<Activity>();
        DatabaseManager db = DatabaseManager.getInstance();
        Activity activity = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM activities");
                while (rs.next()) {
                    activity = new Activity(false);
                    activity.setId(rs.getInt("id"));
                    activity.setSubject_id(rs.getInt("subject_id"));
                    activity.setType(rs.getInt("type"));
                    activity.setLink(rs.getString("link"));
                    activity.setScrib_id(rs.getInt("scribd_id"));
                    activity.setScrib_key(rs.getString("scribd_key"));
                    activity.setName(rs.getString("name"));
                    activity.setDescription(rs.getString("description"));
                    result.add(activity);
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
                    fields.add(String.valueOf(subject_id));
                    fields.add(String.valueOf(type));
                    fields.add(Util.quote(link));
                    fields.add(String.valueOf(scrib_id));
                    fields.add(Util.quote(scrib_key));
                    fields.add(Util.quote(name));
                    fields.add(Util.quote(description));
                    if (db.insert("activities", "(subject_id,type,link,scribd_id,scribd_key,name,description)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("subject_id=" + String.valueOf(subject_id));
                    fields.add("type="+String.valueOf(type));
                    fields.add("link="+Util.quote(link));
                    fields.add("scribd_id="+String.valueOf(scrib_id));
                    fields.add("scribd_key="+Util.quote(scrib_key));
                    fields.add("name="+Util.quote(name));
                    fields.add("description="+Util.quote(description));
                    if (db.update("activities", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("activities", "id=" + id)) {
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
     * @return the subject_id
     */
    public int getSubject_id() {
        return subject_id;
    }

    /**
     * @param subject_id the subject_id to set
     */
    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
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
        if(link == null) return "";
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the scrib_id
     */
    public int getScrib_id() {
        return scrib_id;
    }

    /**
     * @param scrib_id the scrib_id to set
     */
    public void setScrib_id(int scrib_id) {
        this.scrib_id = scrib_id;
    }

    /**
     * @return the scrib_key
     */
    public String getScrib_key() {
        if(scrib_key == null) return "";
        return scrib_key;
    }

    /**
     * @param scrib_key the scrib_key to set
     */
    public void setScrib_key(String scrib_key) {
        this.scrib_key = scrib_key;
    }

    /**
     * @return the name
     */
    public String getName() {
        if(name == null) return "";
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getDescription() {
        if(description == null) return "";
        return description;
    }

    /**
     * @param name the name to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
