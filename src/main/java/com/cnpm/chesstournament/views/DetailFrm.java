package com.cnpm.chesstournament.views;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.soap.Detail;

import com.cnpm.chesstournament.controllers.dao.HappendMatchDAO;
import com.cnpm.chesstournament.models.HappenedMatch;
import com.cnpm.chesstournament.models.Player;

public class DetailFrm extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private Player player;
    private String[] column = {"Id", "Tên đối thủ", "Mức tăng giảm elo"};
    private JTable detailTable;
    private DefaultTableModel dataModel;
    private JScrollPane pane;

    public DetailFrm(Player player) {
        this.player = player;

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loadData();
    }

    void initWidgets() {
        dataModel = new DefaultTableModel();
        pane = new JScrollPane();
        pane.setBounds((WIDTH - 500) / 2, 70, 500, 500);

        detailTable = new JTable();
        pane.setViewportView(detailTable);
        detailTable.getTableHeader().setBackground(Color.WHITE);
        detailTable.setModel(dataModel);
        detailTable.setBounds((WIDTH - 400) / 2, 10, 400, 400);

        this.add(pane);
    }

    void loadData() {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                HappendMatchDAO happendMatchDAO = new HappendMatchDAO();
                List<HappenedMatch> list = happendMatchDAO.getAllHappenedMatchByPlayer(player);
                
                String[][] data = new String[list.size()][3];

                for (int i = 0; i < list.size(); i++) {
                    HappenedMatch happenedMatch = list.get(i);
                    data[i][0] = "" + happenedMatch.getMatch().getId();
                    data[i][1] = "" + happenedMatch.getMatch().getPlayer2().getName();
                    data[i][2] = "" + happenedMatch.getElo();
                }

                dataModel.setDataVector(data, column);
                dataModel.fireTableDataChanged();
            }
        }).start();
    }
    
}