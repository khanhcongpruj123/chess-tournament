package com.cnpm.chesstournament.controllers.dao;

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
            String sql = "SELECT * FROM tbl_Player";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Player player = new Player(id, name);
                res.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}