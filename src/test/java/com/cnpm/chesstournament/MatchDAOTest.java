package com.cnpm.chesstournament;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.cnpm.chesstournament.controllers.dao.MatchDAO;
import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Player;

public class MatchDAOTest {
	
	@Test
	public void testGetAllMatchByRoundExist() {
		MatchDAO matchDAO = new MatchDAO();
		List<Match> list = matchDAO.getAllMatchByRound(1);
		assertNotEquals(list, null);
		assertEquals(list.size(), 2);
	}
	
	@Test
	public void testGetAllMatchByRoundNotExist() {
		MatchDAO matchDAO = new MatchDAO();
		List<Match> list = matchDAO.getAllMatchByRound(10);
		assertNotEquals(list, null);
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void testSaveSchedule() {
		MatchDAO matchDAO = new MatchDAO();
		try {
			matchDAO.conn.setAutoCommit(false);
			Player player1 = new Player();
			player1.setId(1);
			Player player2 = new Player();
			player2.setId(2);
			List<Match> schedule = Arrays.asList(
					new Match(0, player1, player2)
					); 
			boolean res = matchDAO.saveSchedule(schedule);
			assertEquals(res, true);
			matchDAO.conn.rollback();
			matchDAO.conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
