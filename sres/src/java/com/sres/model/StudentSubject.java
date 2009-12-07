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
public class StudentSubject {

    private int id;
    private int subject_id;
    private int user_id;
    private Double final_grade;
    private boolean newRecord = false;

    public StudentSubject(boolean newRecord) {
        this.newRecord = newRecord;
        id = 0;

        subject_id = 0;
        user_id = 0;
        final_grade = 0.0;
    }

    StudentSubject() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getSubjectName() {
        String name = "";
        StudentSubject student_subject = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT name FROM subjects s, competition c WHERE s.competition_id = c.id and s.id=" + subject_id);
                if (rs.next()) {
                    name = rs.getString("id");
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }

        }
        db.close();

        return name;
    }

    public static StudentSubject find_by_id(
            String id) {
        StudentSubject student_subject = null;
        DatabaseManager db = DatabaseManager.getInstance();
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM student_subjects WHERE id=" + id);
                if (rs.next()) {
                    student_subject = new StudentSubject(false);
                    student_subject.setId(rs.getInt("id"));
                    student_subject.setSubject_id(rs.getInt("subject_id"));
                    student_subject.setUser_id(rs.getInt("user_id"));
                    student_subject.setFinal_grade(rs.getDouble("final_grade"));

                }

            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
            }

        }
        db.close();
        return student_subject;
    }

    public static ArrayList<StudentSubject> all() {
        ArrayList<StudentSubject> result = new ArrayList<StudentSubject>();
        DatabaseManager db = DatabaseManager.getInstance();
        StudentSubject student_subject = null;
        if (db != null) {
            try {
                ResultSet rs = db.getQuery("SELECT * FROM student_subjects");
                while (rs.next()) {
                    student_subject = new StudentSubject(false);
                    student_subject.setId(rs.getInt("id"));
                    student_subject.setSubject_id(rs.getInt("subject_id"));
                    student_subject.setUser_id(rs.getInt("user_id"));
                    student_subject.setFinal_grade(rs.getDouble("final_grade"));
                    result.add(student_subject);
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
                    fields.add(String.valueOf(subject_id));
                    fields.add(String.valueOf(user_id));
                    fields.add(String.valueOf(final_grade));
                    if (db.insert("student_subjects", "(subject_id,student_id,final_grade)", "(" + Util.concat(fields, ",") + ")")) {
                        return true;
                    }

                } else {
                    ArrayList fields = new ArrayList();
                    fields.add("subject_id=" + String.valueOf(subject_id));
                    fields.add("user_id=" + String.valueOf(user_id));
                    fields.add("final_grade=" + String.valueOf(final_grade));
                    if (db.update("student_subjects", Util.concat(fields, ","), "id=" + id)) {
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
                if (db.destroy("student_subjects", "id=" + id)) {
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
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the final_grade
     */
    public Double getFinal_grade() {
        return final_grade;
    }

    /**
     * @param final_grade the final_grade to set
     */
    public void setFinal_grade(Double final_grade) {
        this.final_grade = final_grade;
    }
}
