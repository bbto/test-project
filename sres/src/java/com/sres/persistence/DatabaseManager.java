package com.sres.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 *
 * @author bbto
 */
public class DatabaseManager {

    private static String driver;
    private static String database;
    private static String username;
    private static String password;

    private Connection connection;

    private DatabaseManager() {
        if (unsetConnectionParameters()) {
            try {
                setConnectionParameters();
            } catch (NamingException ex) {
                ex.printStackTrace(System.err);
            }
        }
        connect();

    }

    private boolean unsetConnectionParameters() {
        return (driver == null) || (database == null) || (username == null) || (password == null);
    }

    private void setConnectionParameters() throws NamingException {
        // Obtain the application component's environment naming context
        javax.naming.Context ctx = new javax.naming.InitialContext();
        javax.naming.Context env = (Context) ctx.lookup("java:comp/env");

        // Obtain the environment entries configured on web.xml
        driver = (String) env.lookup("driver");
        database = (String) env.lookup("database");
        username = (String) env.lookup("username");
        password = (String) env.lookup("password");
    }

    private void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(database, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }

    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static DatabaseManager getInstance() {
        return new DatabaseManager();
    }

    public ResultSet getQuery(String sqlQuery) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sqlQuery);
            return resultset;
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }
}
