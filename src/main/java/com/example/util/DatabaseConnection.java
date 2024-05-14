package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(String url, String dbname, String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + dbname + "?serverTimezone=UTC&" + "user=" + username + "&password=" + password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        return DatabaseConnection.getConnection("localhost", "exampapaerdatabase", "root", "yourPassword");
    }
}