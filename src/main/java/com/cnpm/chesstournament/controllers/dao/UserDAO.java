package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cnpm.chesstournament.models.User;

public class UserDAO extends DAO {

    public boolean checkLogin(User user) {

        try {

            Boolean result = false;

            String sql = "SELECT id, username, password, name FROM tbl_User WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setMaxRows(1);
            ResultSet res = statement.executeQuery();

            while(res.next()) {

                result = true;

                long id = res.getLong(1);
                String name = res.getString(4);
                
                user.setId(id);
                user.setName(name);

                System.out.println("Login: " + user.toString());
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}