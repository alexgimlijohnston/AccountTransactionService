package com.service.dao;

import org.h2.tools.DeleteDbFiles;
import java.sql.*;

public class DbConfig {

    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_CONNECTION = "jdbc:h2:~/test";
    public static final String DB_USER = "";
    public static final String DB_PASSWORD = "";

    private static Connection connection = null;

    private static Statement stmt = null;

    public Statement getStmt() {
        return stmt;
    }


    public static Connection getInMemoryDatabase() {
        if(connection == null) {
            //delete the H2 database named 'test' in the user home directory
            DeleteDbFiles.execute("~", "test", true);
            createTables();
            return connection;
        } else {
            return connection;
        }
    }

    private static void createTables() {
        connection = getDBConnection();
        stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE ACCOUNT(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO ACCOUNT(id, name) VALUES(100, 'Alex')");
            stmt.execute("INSERT INTO ACCOUNT(id, name) VALUES(200, 'Victoria')");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


}
