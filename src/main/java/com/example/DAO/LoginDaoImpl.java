package com.example.DAO;

import com.example.model.Answer;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean validateUser(String username, String password) {
        boolean isValidUser = false;
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM t_user WHERE username= ? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                isValidUser = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValidUser;
    }
}
