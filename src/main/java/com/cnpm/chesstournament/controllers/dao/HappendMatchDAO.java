package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.chesstournament.models.HappenedMatch;
import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Player;

public class HappendMatchDAO extends DAO {
     
    public List<HappenedMatch> getAllHappenedMatchByPlayer(Player player) {
        System.out.println("" + player.getId());
        List<HappenedMatch> res = new ArrayList<>();
        String sql = "SELECT tbl_Match.id, p.name,tbl_HappenedMatch.elo FROM tbl_HappenedMatch, tbl_Match, tbl_Player, (SELECT tbl_Player.id, tbl_Player.name FROM tbl_Player) as p WHERE tbl_HappenedMatch.player_id = tbl_Player.id AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_Player.id = ? AND NOT p.id = tbl_Player.id AND (tbl_Match.player2_id = p.id OR tbl_Match.player1_id = p.id)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, player.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long elo = resultSet.getLong(3);

                Player player1 = player;
                Player player2 = new Player();
                player2.setName(name);

                Match match = new Match();
                match.setId(id);
                match.setPlayer1(player1);
                match.setPlayer2(player2);

                HappenedMatch happenedMatch = new HappenedMatch();
                happenedMatch.setMatch(match);
                happenedMatch.setPlayer(player1);
                happenedMatch.setElo(elo);

                res.add(happenedMatch);
            }

            return res;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

	public boolean createHappenedMatch(HappenedMatch happenedMatch1, HappenedMatch happenedMatch2) {
        try {
            conn.setAutoCommit(false);

            String sql1 = "REPLACE INTO tbl_HappenedMatch VALUES (?, ?, ?, ?)";
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setLong(1, happenedMatch1.getPlayer().getId());
            statement1.setLong(2, happenedMatch1.getMatch().getId());
            statement1.setLong(3, happenedMatch1.getPoint());
            statement1.setLong(4, happenedMatch1.getElo());
            int res1 = statement1.executeUpdate();
            if (res1 == 0) { 
                conn.rollback();
                conn.setAutoCommit(true);
                return false;
            }

            String sql2 = "REPLACE tbl_HappenedMatch VALUES (?, ?, ?, ?)";
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setLong(1, happenedMatch2.getPlayer().getId());
            statement2.setLong(2, happenedMatch2.getMatch().getId());
            statement2.setLong(3, happenedMatch2.getPoint());
            statement2.setLong(4, happenedMatch2.getElo());
            int res2 = statement2.executeUpdate();

            if (res2 == 0) { 
                conn.rollback();
                conn.setAutoCommit(true);
                return false;
            }

            conn.commit();
            conn.setAutoCommit(true);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
}