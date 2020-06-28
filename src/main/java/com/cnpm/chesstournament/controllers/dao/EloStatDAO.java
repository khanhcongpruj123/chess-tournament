package com.cnpm.chesstournament.controllers.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.cnpm.chesstournament.models.EloStat;

public class EloStatDAO extends DAO {

    public List<EloStat> getAllEloStat() {
        List<EloStat> res = new ArrayList<>();
        String sql = "SELECT tbl_Player.id, name, birth_year, nationality, oldElo, elo FROM tbl_Player INNER JOIN tbl_HappenedMatch ON tbl_Player.id = tbl_HappenedMatch.player_id";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long birthYear = resultSet.getLong(3);
                String nationality = resultSet.getString(4);
                long oldElo = resultSet.getLong(5);

                long newElo = getEloPlayerById(id) + oldElo;
                EloStat eloStat = new EloStat(id, name, birthYear, nationality, oldElo, newElo);

                System.out.println(eloStat.toString());
                res.add(eloStat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    private long getEloPlayerById(long id) {
        String sql = "SELECT SUM(tbl_HappenedMatch.elo) FROM tbl_HappenedMatch JOIN tbl_Player ON tbl_HappenedMatch.player_id = tbl_Player.id WHERE tbl_Player.id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long elo = resultSet.getLong(1);
                return elo;
            }
            return 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}