package com.cnpm.chesstournament.views;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.PlayerDAO;
import com.cnpm.chesstournament.models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerManagerFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private JTable playerTable;
    private DefaultTableModel dataModel;
    private String[] colums = {"id", "name"};

    public PlayerManagerFrm() {

        this.setSize(WIDTH, HEIGHT);

        initWidgets();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        loadData();
    }

    void initWidgets() {

        dataModel = new DefaultTableModel();
        dataModel.setColumnIdentifiers(colums);

        playerTable = new JTable();
        playerTable.setBounds((WIDTH - 300) / 2, 10, 300, 300);
        playerTable.setModel(dataModel);

        this.add(playerTable);
    }

    void loadData() {
        PlayerDAO playerDAO = new PlayerDAO();
        List<Player> res = playerDAO.getAllPlayer();
        String[][] data = new String[res.size()][3];

        for (int i = 0; i < res.size(); i++) {
            Player player = res.get(i);
            data[i][0] = "" + player.getId();
            data[i][1] = "" + player.getName();
        }

        dataModel.setDataVector(data, colums);
        dataModel.notifyAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}