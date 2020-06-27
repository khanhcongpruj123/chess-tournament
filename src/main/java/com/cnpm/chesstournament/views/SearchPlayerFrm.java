package com.cnpm.chesstournament.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.PlayerDAO;
import com.cnpm.chesstournament.models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchPlayerFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTextField edtPlayerName;
    private JButton btnSearch;
    private DefaultTableModel dataModel;
    private JTable playerTable;
    private String[] colums = {"id", "Ten", "Nam Sinh", "Quoc Tich"};

    public SearchPlayerFrm() {
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initWidgets() {
        edtPlayerName = new JTextField();
        btnSearch = new JButton("Tìm kiếm");

        edtPlayerName.setBounds(10, 10, 200, 50);
        btnSearch.setBounds(220, 10, 100, 50);
        btnSearch.addActionListener(this);

        dataModel = new DefaultTableModel();
        dataModel.setColumnIdentifiers(colums);

        playerTable = new JTable();
        playerTable.setBounds((WIDTH - 300) / 2, 60, 300, 300);
        playerTable.setModel(dataModel);

        this.add(playerTable);
        this.add(edtPlayerName);
        this.add(btnSearch);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnSearch))) {
            new Thread(new Runnable(){
            
                @Override
                public void run() {
                    String txt = edtPlayerName.getText();
                    String key = txt.trim();
                    key.toLowerCase();

                    System.out.println("Key: " + key);

                    PlayerDAO playerDAO = new PlayerDAO();
                    List<Player> res = playerDAO.getPlayerByKey(key);

                    String[][] data = new String[res.size()][4];

                    for (int i = 0; i < res.size(); i++) {
                        Player player = res.get(i);
                        data[i][0] = "" + player.getId();
                        data[i][1] = "" + player.getName();
                        data[i][2] = "" + player.getBirthYear();
                        data[i][3] = "" + player.getNationality();
                    }

                    dataModel.setDataVector(data, colums);
                    dataModel.fireTableDataChanged();
                }
            }).start();
        }
    }
    
}