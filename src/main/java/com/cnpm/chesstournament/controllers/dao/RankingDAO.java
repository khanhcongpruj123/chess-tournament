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
                long point = 0 ;
                long totalPointOfCompetior = 0;
                long elo = 0;
                if (roundIndex != 0) {
                   elo = getEloPlayerById(id, roundIndex) + oldElo;
                } else {
                    elo = oldElo;
                }

                if (roundIndex != 0) {
                    totalPointOfCompetior = getTotalPointOfCompetior(id, roundIndex);
                } else {
                    totalPointOfCompetior = 0;
                }

                if (roundIndex != 0) {
                    point = getPointPlayerById(id, roundIndex);
                } else {
                    point = 0;
                }



                Ranking player = new Ranking(id, name, birthYear, nationality, point, totalPointOfCompetior, elo); 
                res.add(player);
            }

            res.sort(new Comparator<Ranking>() {

                @Override
                public int compare(Ranking o1, Ranking o2) {
                    if (o1.getPoint() > o2.getPoint()) {
                        return -1;
                    } else if (o1.getPoint() < o2.getPoint()) {
                        return 1;
                    } else if (o1.getElo() > o2.getElo()) {
                        return -1;
                    } else if (o1.getElo() < o2.getElo()) {
                        return 1;
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
        System.out.println("round index: " + roundIndex + "id: " + id);
        String sql = "SELECT SUM(h2.point) FROM tbl_Player as p1, tbl_HappenedMatch as h1, tbl_Player as p2, tbl_HappenedMatch as h2, tbl_Match, tbl_Round WHERE p1.id = ? AND NOT p1.id = p2.id AND p1.id = h1.player_id AND p2.id = h2.player_id AND tbl_Match.id = h1.match_id AND tbl_Round.id = tbl_Match.round_id AND p2.id IN (tbl_Match.player2_id, tbl_Match.player1_id) AND h2.match_id = h1.match_id AND tbl_Round.round <= ?";
        try {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setLong(2, roundIndex);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    private long getEloPlayerById(long id, long roundIndex) {
        String sql = "SELECT SUM(tbl_HappenedMatch.elo) FROM tbl_Player, tbl_HappenedMatch, tbl_Match, tbl_Round WHERE tbl_Player.id = tbl_HappenedMatch.player_id AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_Match.round_id = tbl_Round.id AND tbl_Round.round <= ?  AND tbl_Player.id = ?";
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
        String sql = "SELECT SUM(tbl_HappenedMatch.point) FROM tbl_Player, tbl_HappenedMatch, tbl_Match, tbl_Round WHERE tbl_Player.id = tbl_HappenedMatch.player_id AND tbl_HappenedMatch.match_id = tbl_Match.id AND tbl_Match.round_id = tbl_Round.id AND tbl_Round.round <= ? AND tbl_Player.id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, roundIndex);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long point = resultSet.getLong(1);
                return point;
            }
            return 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

	public List<Ranking> getRandking() {
        String sql = "SELECT * FROM tbl_Player";
        List<Ranking> res = new ArrayList<Ranking>();
        String getLastedIdRoundSql = "SELECT MAX(tbl_Round.round) FROM tbl_Round";
        try {

            // lay thu tu round cuoi cung de tao round moi, bang cach + 1
            ResultSet set = conn.createStatement().executeQuery(getLastedIdRoundSql);
            long lastedRound = 0;
            while(set.next()) {
                lastedRound = set.getLong(1);
                break;
            }
            
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                long birthYear = resultSet.getLong(3);
                String nationality = resultSet.getString(4);
                long oldElo = resultSet.getLong(5);     
                long point = getPointPlayerById(id, lastedRound);
                long totalPointOfCompetior = getTotalPointOfCompetior(id, lastedRound);
                long elo = getEloPlayerById(id, lastedRound) + oldElo;

                Ranking player = new Ranking(id, name, birthYear, nationality, point, totalPointOfCompetior, elo); 
                res.add(player);
            }

            res.sort(new Comparator<Ranking>() {

                @Override
                public int compare(Ranking o1, Ranking o2) {
                    if (o1.getPoint() > o2.getPoint()) {
                        return -1;
                    } else if (o1.getPoint() < o2.getPoint()) {
                        return 1;
                    } else if (o1.getElo() > o2.getElo()) {
                        return -1;
                    } else if (o1.getElo() < o2.getElo()) {
                        return 1;
                    }
                    return 0;
                }

            });

            return res;
        } catch(SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
	}
    
}