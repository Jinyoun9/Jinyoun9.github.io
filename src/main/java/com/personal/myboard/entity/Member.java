package com.personal.myboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.*;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;


    public void changePassword(String oldPassword, String newPassword){
        if(!password.equals(oldPassword)){
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }
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
    public Member init(){
            Member member = new Member();
            member.setUsername("pjy1121");
            member.setPassword("eksldpf13");
            return member;
    }
}
