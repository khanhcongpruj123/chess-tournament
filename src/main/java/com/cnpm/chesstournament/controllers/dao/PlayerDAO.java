package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.chesstournament.models.Player;

public class PlayerDAO extends DAO {

    public List<Player> getAllPlayer() {
        List<Player> res = new ArrayList<>();
        try {
            String sql = "SELECT id, name, birth_year, nationality FROM tbl_Player";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long birthYear = resultSet.getLong(3);
                String nationality = resultSet.getString(4);
                Player player = new Player(id, name, birthYear, nationality);
                res.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Player> getPlayerByKey(String key) {
        List<Player> res = new ArrayList<>();
        try {
            String sql = "SELECT id, name, birth_year, nationality FROM tbl_Player WHERE name LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + key + "%");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long birthYear = resultSet.getLong(3);
                String nationality = resultSet.getString(4);
                Player player = new Player(id, name, birthYear, nationality);
                System.out.println("Player: " + player.toString());
                res.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}