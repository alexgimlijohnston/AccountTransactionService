package com.service.dao;

import org.h2.tools.DeleteDbFiles;
import java.io.*;
import java.sql.*;
import java.util.Objects;
import java.util.stream.Collectors;

public class DbConfig {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_TABLES = "db-tables";
    private static final String DB_DATA = "db-data";

    private static Connection connection = null;

    public static Connection getInMemoryDatabase() {
        if(connection == null) {
            DeleteDbFiles.execute("~", "test", true);
            setUpDatabase();
            return connection;
        } else {
            return connection;
        }
    }

    private static void setUpDatabase() {
        connection = getDBConnection();
        try {
            Objects.requireNonNull(connection).setAutoCommit(false);
            Statement stmt = connection.createStatement();
            executeQueries(stmt, DB_TABLES);
            executeQueries(stmt, DB_DATA);
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeQueries(Statement stmt, String path) throws IOException, SQLException {
        InputStream in = DbConfig.class.getClassLoader().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in)));
        String fileName;
        while ((fileName = br.readLine()) != null) {
            String[] queries = readSql(path + "/" + fileName);
            for (String query : queries) {
                stmt.execute(query);
            }
        }
    }

    private static String[] readSql(String resourceName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DbConfig.class.getClassLoader().getResourceAsStream(resourceName))));
        String s = reader.lines().collect(Collectors.joining());
        reader.close();
        return s.split(";");
    }

    private static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
