package com.cnpm.chesstournament.views;

import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.EloStatDAO;
import com.cnpm.chesstournament.models.EloStat;



public class RankingFrm extends JFrame {
    
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable rankingTable;
    private DefaultTableModel model;
    private final String[] colums = {"Rank", "PlayerID", "Name", "Elo"};

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
        
        rankingTable.setBounds((WIDTH - 300) / 2, 10,  300, 300);
        this.add(rankingTable);
    }

    void loadData() {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                EloStatDAO eloStatDAO = new EloStatDAO();
                List<EloStat> list = eloStatDAO.getAllEloStat();
                list.sort(new Comparator<EloStat>() {

                    @Override
                    public int compare(EloStat o1, EloStat o2) {
                        if (o1.getNewElo() > o2.getNewElo()) return 1;
                        else if (o1.getNewElo() < o2.getNewElo()) return -1;
                        else if (o1.getName().compareTo(o2.getName()) < 0) return 1;
                        else return -1;
                    }
                });

                String[][] data = new String[list.size()][4];

                for (int i = 0; i < list.size(); i++) {
                    EloStat player = list.get(i);
                    data[i][0] = "" + (i + 1);
                    data[i][1] = "" + player.getId();
                    data[i][2] = "" + player.getName();
                }

            model.setDataVector(data, colums);
            }
        }).start();;
    }
}