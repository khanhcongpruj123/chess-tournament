package com.cnpm.chesstournament.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.MatchDAO;
import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Round;

public class ScheduleFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable matchTable;
    private DefaultTableModel dataModel;
    private String[] columns = { "Cap dau", "Ten nguoi choi 1", "Ten nguoi choi 2" };
    private List<Round> listRound;
    private List<Match> schedule;
    private JButton btnSave;
    private JButton btnCancel;

    public ScheduleFrm(List<Match> schedule) {

        this.schedule = schedule;

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initWidgets() {

        final String[][] data = new String[schedule.size()][3];

        for (int i = 0; i < schedule.size(); i++) {
            final Match match = schedule.get(i);
            data[i][0] = "" + (i + 1);
            data[i][1] = "" + match.getPlayer1().getName();
            data[i][2] = "" + match.getPlayer2().getName();
        }

        dataModel = new DefaultTableModel(data, columns);
        btnCancel = new JButton("Bỏ");
        btnSave = new JButton("Lưu");
        btnCancel.setBounds(10, 10, 100, 50);
        btnSave.setBounds(120, 10, 100, 50);
        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);

        matchTable = new JTable();
        matchTable.setModel(dataModel);
        matchTable.setBounds((WIDTH - 500) / 2, 70, 500, 500);

        this.add(matchTable);
        this.add(btnSave);
        this.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton&& e.getSource().equals(btnCancel)) {
            dispose();
        } else if (e.getSource() instanceof JButton&& e.getSource().equals(btnSave)) {
            MatchDAO matchDAO = new MatchDAO();
            boolean res = matchDAO.saveSchedule(schedule);
            if (res) {
                dispose();
            }
        }
    }
}