package com.cnpm.chesstournament;

import static org.junit.Assert.*;

import java.util.List;

import com.cnpm.chesstournament.controllers.dao.EloStatDAO;
import com.cnpm.chesstournament.models.EloStat;

import org.junit.Test;

public class EloStatDAOTest {

    @Test
    public void testGetAllEloStat() {
        EloStatDAO eloStatDAO = new EloStatDAO();
        List<EloStat> list = eloStatDAO.getAllEloStat();
        assertNotEquals(list, null);
        assertEquals(list.size(), 5);
    }
    
    @Test
    public void testEloPlayerByIdWithExistId() {
    	EloStatDAO eloStatDAO = new EloStatDAO();
    	long elo = eloStatDAO.getEloPlayerById(1);
    	assertEquals(elo, 300);
    }
    
    @Test
    public void testEloPlayerByIdWithNotExistId() {
    	EloStatDAO eloStatDAO = new EloStatDAO();
    	long elo = eloStatDAO.getEloPlayerById(100);
    	assertEquals(elo, 0);
    }
    
}