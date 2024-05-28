package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection(String url, String dbname, String username, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 手动注册驱动程序
            conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + dbname + "?serverTimezone=UTC&" + "user=" + username + "&password=" + password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        return DatabaseConnection.getConnection("localhost", "exampaperdatabase", "root", "st75s08mn07");
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
