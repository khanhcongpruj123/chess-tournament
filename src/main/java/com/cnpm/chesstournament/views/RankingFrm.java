package com.cnpm.chesstournament.views;

import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.EloStatDAO;
import com.cnpm.chesstournament.controllers.dao.RankingDAO;
import com.cnpm.chesstournament.models.EloStat;
import com.cnpm.chesstournament.models.Ranking;



public class RankingFrm extends JFrame {
    
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable rankingTable;
    private DefaultTableModel model;
    private final String[] colums = {"Id", "Tên", "Năm sinh","Quốc tịch", "Tổng điểm", "Tổng điểm đối thủ đã gặp", "Elo"};
    public RankingFrm() {

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    
        loadData();
    }

    void initWidgets() {
        rankingTable = new JTable();
        model = new DefaultTableModel();
        rankingTable.setModel(model);
        
        rankingTable.setBounds((WIDTH - 500) / 2, 10,  500, 500);
        this.add(rankingTable);
    }

    void loadData() {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                RankingDAO rankingDAO = new RankingDAO();
                List<Ranking> list = rankingDAO.getRankingByRound(1);
            

                String[][] data = new String[list.size()][7];

                for (int i = 0; i < list.size(); i++) {
                    Ranking player = list.get(i);
                    data[i][0] = "" + player.getId();
                    data[i][1] = "" + player.getName();
                    data[i][2] = "" + player.getBirthYear();
                    data[i][3] = "" + player.getNationality();
                    data[i][4] = "" + player.getPoint();
                    data[i][5] = "" + player.getTotalPointOfCompetitor();
                    data[i][5] = "" + player.getElo();
                }

                model.setDataVector(data, colums);
            }
        }).start();;
    }
}