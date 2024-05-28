package com.example.DAO;

import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDaoImpl implements RegisterDao {
    @Override
    public boolean registerUser(String username, String password) {
        boolean isRegistered = false;

        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.out.println("Failed to make connection!");
                return isRegistered;
            }
            String sql = "INSERT INTO t_user (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                isRegistered = true;
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRegistered;
    }
}
