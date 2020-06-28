package com.cnpm.chesstournament.controllers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.cnpm.chesstournament.models.Player;
import com.cnpm.chesstournament.models.Ranking;

public class RankingDAO extends DAO {

    public List<Ranking> getRankingByRound(long roundIndex) {
        String sql = "SELECT * FROM tbl_Player";
        List<Ranking> res = new ArrayList<Ranking>();
        try {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long birthYear = resultSet.getLong(3);
                String nationality = resultSet.getString(4);
                long oldElo = resultSet.getLong(5);
                long point = getPointPlayerById(id, roundIndex);
                long totalPointOfCompetior = getTotalPointOfCompetior(id, roundIndex);
                long elo = getEloPlayerById(id, roundIndex) + oldElo;

                Ranking player = new Ranking(id, name, birthYear, nationality, point, totalPointOfCompetior, elo); 
                res.add(player);
            }

            res.sort(new Comparator<Ranking>() {

                @Override
                public int compare(Ranking o1, Ranking o2) {
                    if (o1.getPoint() > o2.getPoint()) {
                        return 0;
                    } else if (o1.getPoint() < o2.getPoint()) {
                        return -1;
                    } else if (o1.getElo() > o2.getElo()) {
                        return 1;
                    } else if (o1.getElo() < o2.getElo()) {
                        return -1;
                    }
                    return 0;
                }

            });

            return res;

        } catch(SQLException e) {
            e.printStackTrace();
            return new ArrayList<Ranking>();
        }
    }

    private long getTotalPointOfCompetior(long id, long roundIndex) {
        String sql = "SELECT SUM(tbl_HappenedMatch.point) FROM tbl_Player, tbl_HappenedMatch, tbl_Match, tbl_Round WHERE (tbl_Match.player1_id = 1 OR tbl_Match.player2_id = 1) AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_HappenedMatch.player_id = tbl_Player.id AND tbl_Round.id <= ? AND NOT tbl_HappenedMatch.player_id = ?";
        try {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, roundIndex);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    private long getEloPlayerById(long id, long roundIndex) {
        String sql = "SELECT SUM(tbl_HappenedMatch.elo) FROM tbl_Player, tbl_HappenedMatch, tbl_Match, tbl_Round WHERE tbl_Player.id = tbl_HappenedMatch.player_id AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_Match.round_id <= ? AND tbl_Player.id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, roundIndex);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long elo = resultSet.getLong(1);
            return elo;
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private long getPointPlayerById(long id, long roundIndex) {
        String sql = "SELECT SUM(tbl_HappenedMatch.point) FROM tbl_Player, tbl_HappenedMatch, tbl_Match, tbl_Round WHERE tbl_Player.id = tbl_HappenedMatch.player_id AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_Match.round_id <= ? AND tbl_Player.id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, roundIndex);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long point = resultSet.getLong(1);
            return point;
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}