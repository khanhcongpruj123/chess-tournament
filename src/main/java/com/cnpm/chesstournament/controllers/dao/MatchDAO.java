package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Player;

public class MatchDAO extends DAO {

    public List<Match> getAllMatchByRound(long roundIndex) {
        List<Match> res = new ArrayList<>();
        String sql = "SELECT DISTINCT tbl_Match.id, p1.id, p1.`name`, p2.id, p2.`name` FROM tbl_Match, tbl_Player, (SELECT DISTINCT tbl_Player.id, tbl_Player.name FROM tbl_Player) as p1, (SELECT DISTINCT tbl_Player.id, tbl_Player.name FROM tbl_Player) as p2 WHERE p1.id = tbl_Match.player1_id AND p2.id = tbl_Match.player2_id AND tbl_Match.round_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, roundIndex);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                long id1 = resultSet.getLong(2);
                String name1 = resultSet.getString(3);
                long id2 = resultSet.getLong(4);
                String name2 = resultSet.getString(5);

                Player player1 = new Player();
                Player player2 = new Player();

                player1.setId(id1);
                player1.setName(name1);
                player2.setId(id2);
                player2.setName(name2);

                Match match = new Match(id, player1, player2);
                res.add(match);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return res;
    }
    
}