package com.cnpm.chesstournament.controllers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public static Connection conn;

    public DAO() {
        if (conn == null) {
            System.out.println("Creating connection...");
            String dbUrl = "jdbc:mysql://localhost:3306/chesstournament?autoReconnect=true&useSSL=false";
            try {
                conn = DriverManager.getConnection(dbUrl, "root", "");
                System.out.println("Creating connection success!");
            } catch (SQLException e) {
                System.out.println("Create connection fail!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "{" +
            "}";
    }

}