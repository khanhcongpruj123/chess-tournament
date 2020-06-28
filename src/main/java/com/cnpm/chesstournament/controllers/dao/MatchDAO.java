package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Player;
import com.cnpm.chesstournament.models.Round;

public class MatchDAO extends DAO {

    public List<Match> getAllMatchByRound(long roundIndex) {
        List<Match> res = new ArrayList<>();
        String sql = "SELECT DISTINCT tbl_Match.id, p1.id, p1.`name`, p2.id, p2.`name` FROM tbl_Match, tbl_Player, tbl_Round, (SELECT DISTINCT tbl_Player.id, tbl_Player.name FROM tbl_Player) as p1, (SELECT DISTINCT tbl_Player.id, tbl_Player.name FROM tbl_Player) as p2 WHERE p1.id = tbl_Match.player1_id AND p2.id = tbl_Match.player2_id AND tbl_Match.round_id = tbl_Round.id AND tbl_Round.round = ?";
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

    public boolean saveSchedule(List<Match> schedule) {
        try {
            String getLastedIdRoundSql = "SELECT MAX(tbl_Round.round) FROM tbl_Round";
            String createRoundSql = "INSERT INTO tbl_Round(round) VALUES (?)";
            String createMatchSql = "INSERT INTO tbl_Match(player1_id, player2_id, round_id) VALUES(?, ?, ?)";
            String getLastedRoundSql = "SELECT tbl_Round.id, tbl_Round.round FROM tbl_Round, (SELECT MAX(tbl_Round.round) as maxround FROM tbl_Round) as r WHERE r.maxround = tbl_Round.round";

            // lay thu tu round cuoi cung de tao round moi, bang cach + 1
            ResultSet resultSet = conn.createStatement().executeQuery(getLastedIdRoundSql);
            long lastedRound = 0;
            while(resultSet.next()) {
                lastedRound = resultSet.getLong(1);
                break;
            }

            // begin transaction
            conn.setAutoCommit(false);

            // tao round dau moi
            PreparedStatement statement = conn.prepareStatement(createRoundSql);
            statement.setLong(1, lastedRound + 1);
            int res = statement.executeUpdate();
            if (res == 0) {// thoat neu round dau moi chua duoc tao
                conn.rollback();
                conn.setAutoCommit(true);
                return false;
            }

            // lay round vua insert 
            PreparedStatement state = conn.prepareStatement(getLastedRoundSql);
            ResultSet set = state.executeQuery();
            Round lastRound = null;
            while(set.next()) {
                long id = set.getLong(1);
                long round = set.getLong(2);
                lastRound = new Round(id, round);
                break;
            }

            // insert match vao round moi
            if (lastRound == null) { // kiem tra neu round moi chua dc tao thi roll back va thoat
                conn.rollback();
                conn.setAutoCommit(true);
                return false;
            }

            for (Match i : schedule) {
                PreparedStatement s = conn.prepareStatement(createMatchSql);
                s.setLong(1, i.getPlayer1().getId());
                s.setLong(2, i.getPlayer2().getId());
                s.setLong(3, lastRound.getId());
                int r = s.executeUpdate();
                if (r == 0) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return false;
                }
            }

            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}