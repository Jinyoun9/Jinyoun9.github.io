package com.personal.myboard.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    private static String username, password;

    private static final String url = "jdbc:mariadb://localhost:3306/myboard";

    public boolean authenticateUser(String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            // 사용자 조회 쿼리
            String sql = "SELECT * FROM member WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 쿼리 실행 및 결과 가져오기
            ResultSet rs = pstmt.executeQuery();
            // 사용자가 존재하는 경우 true 반환
            if (rs.next()) {
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                return false;
            }
        }
        return false;
    }
}
