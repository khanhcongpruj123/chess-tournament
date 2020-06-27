package com.cnpm.chesstournament.views;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cnpm.chesstournament.controllers.dao.EloStatDAO;
import com.cnpm.chesstournament.models.EloStat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EloStatFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable eloTable;
    private DefaultTableModel dataModel;
    private String[] colums = {"id", "name", "oldElo", "newElo"};

    public EloStatFrm() {

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

        eloTable = new JTable();
        eloTable.setModel(dataModel);
        eloTable.setBounds((WIDTH - 300) / 2, 10, 300, 300);

        this.add(eloTable);
    }

    void loadData() {

        new Thread(new Runnable(){
        
            @Override
            public void run() {
                EloStatDAO eloStatDAO = new EloStatDAO();
                List<EloStat> res = eloStatDAO.getAllEloStat();
                String[][] data = new String[res.size()][4];

                for (int i = 0; i < res.size(); i++) {
                    EloStat player = res.get(i);
                    data[i][0] = "" + player.getId();
                    data[i][1] = "" + player.getName();
                    data[i][2] = "" + player.getOldElo();
                    data[i][3] = "" + player.getNewElo();
                }

                dataModel.setDataVector(data, colums);
                synchronized(dataModel) {
                    dataModel.notifyAll();
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}