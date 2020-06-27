package com.cnpm.chesstournament.views;

import javax.swing.JButton;
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
    private JButton btnUpdate;
    private JButton btnAdd;
    private JButton btnDelete;
    private DefaultTableModel dataModel;
    private String[] colums = {"id", "Ten", "Nam Sinh", "Quoc Tich"};

    public PlayerManagerFrm() {

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // loadData();
    }

    void initWidgets() {

        // dataModel = new DefaultTableModel();
        // dataModel.setColumnIdentifiers(colums);

        // playerTable = new JTable();
        // playerTable.setBounds((WIDTH - 300) / 2, 10, 300, 300);
        // playerTable.setModel(dataModel);

        // this.add(playerTable);

        btnUpdate = new JButton("Cập nhật");
        btnDelete = new JButton("Xoá");
        btnAdd = new JButton("Thêm");

        btnUpdate.addActionListener(this);
        btnUpdate.setBounds(10, 10, 200, 50);

        this.add(btnUpdate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnUpdate))) {
            SearchPlayerFrm searchPlayerFrm = new SearchPlayerFrm();
        }
    }
    
}