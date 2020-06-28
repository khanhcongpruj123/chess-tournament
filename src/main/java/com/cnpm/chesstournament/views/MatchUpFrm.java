package com.cnpm.chesstournament.views;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MatchUpFrm extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable eloTable;
    private DefaultTableModel dataModel;
    private String[] rankColumns = {"Id", "Tên", "Năm sinh", "Quốc tịch"};

    public MatchUpFrm() {
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        // initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // loadData();
    }
    
}