package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(String url, String dbname, String username, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + dbname + "?serverTimezone=UTC&" + "user=" + username + "&password=" + password);
        } catch (SQLException  e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Connection getConnection() {
        // Provide your database connection details here
        return DatabaseConnection.getConnection("localhost:3306 ", "exampaperdb", "Your Username Here", "Your Password Here");
    }

    public static void main(String[] args) {
        // Get connection
        Connection connection = getConnection();
        // Check if connection is successful
        if (connection != null) {
            System.out.println("Database connected successfully!");
            // Close the connection to avoid resource leaks
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
