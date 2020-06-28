package com.cnpm.chesstournament.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cnpm.chesstournament.models.Round;

public class RoundDAO extends DAO {
    
    public List<Round> getAllRound() {
        List<Round> res = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbl_Round";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                long round = resultSet.getLong(2);
                Round player = new Round(id, round);
                res.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res; 
    }
}