package com.example.schedule;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/schedule";
        String username = "root";
        String password = "tkyeom1478@";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("MySQL 연결 성공");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MySQL 연결 실패");
        }
    }
}
