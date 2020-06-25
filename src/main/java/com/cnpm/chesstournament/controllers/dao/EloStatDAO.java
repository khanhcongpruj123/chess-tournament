package com.cnpm.chesstournament.controllers.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.cnpm.chesstournament.models.EloStat;

public class EloStatDAO extends DAO {

    public List<EloStat> getAllEloStat() {
        List<EloStat> res = new ArrayList<>();
        String sql = "SELECT id, name, oldElo, newElo FROM tbl_Player INNER JOIN tbl_EloStat ON tbl_Player.id = tbl_EloStat.player_id";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long oldElo = resultSet.getLong(3);
                long newElo = resultSet.getLong(4);

                EloStat eloStat = new EloStat(id, name, oldElo, newElo);
                System.out.println(eloStat.toString());
                res.add(eloStat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
    
}