package com.example.courseworkappelectives;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBase {
    private static final DataBase INSTANCE = new DataBase();
    private static final String URL  = "jdbc:mysql://localhost:3306/electives?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private DataBase() {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new RuntimeException(e); }
    }
    public static DataBase getInstance() { return INSTANCE; }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
