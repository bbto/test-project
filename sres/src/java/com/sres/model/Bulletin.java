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
public class Bulletin {

    private int id;
    private int subject_id;
    private String context;
    private boolean newRecord = false;

    public Bulletin(boolean newRecord) {
        this.newRecord = newRecord;
        id = 0;
        subject_id = 0;
        context = null;
    }

    public static Bulletin find_by_id(String id) {
        Bulletin bulletin = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM bulletins WHERE id=" + id);
                if (rs.next()) {
                    bulletin = new Bulletin(false);
                    bulletin.setId(rs.getInt("id"));
                    bulletin.setSubject_id(rs.getInt("subject_id"));
                    bulletin.setContext(rs.getString("context"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }
        }
        db.close();
        return bulletin;
    }

    public static ArrayList<Bulletin> all() {
        ArrayList<Bulletin> result = new ArrayList<Bulletin>();
        DatabaseManager db = DatabaseManager.getInstance();
        Bulletin bulletin = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM bulletins");
                while (rs.next()) {
                    bulletin = new Bulletin(false);
                    bulletin.setId(rs.getInt("id"));
                    bulletin.setSubject_id(rs.getInt("subject_id"));
                    bulletin.setContext(rs.getString("context"));
                    result.add(bulletin);
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
                    fields.add(Util.quote(context));
                    if (db.insert("bulletins", "(subject_id,context)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }
                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("subject_id=" + String.valueOf(subject_id));
                    fields.add("context=" + Util.quote(context));
                    if (db.update("bulletins", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("bulletins", "id=" + id)) {
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
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
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
