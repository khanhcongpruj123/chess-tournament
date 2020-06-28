package com.cnpm.chesstournament.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.RankingDAO;
import com.cnpm.chesstournament.controllers.dao.RoundDAO;
import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Player;
import com.cnpm.chesstournament.models.Ranking;
import com.cnpm.chesstournament.models.Round;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MatchUpFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private String[] rankColumns = { "Id", "Tên", "Năm sinh", "Quốc tịch" };
    private JComboBox roundBox;
    private JTable rankingTable;
    private DefaultTableModel dataModel;
    private DefaultComboBoxModel roundModel;
    private String[] columns = { "ID", "Ten nguoi choi 1", "Ten nguoi choi 2" };
    private List<Round> listRound;
    private List<Ranking> list;
    private JButton btnMatchUp;

    public MatchUpFrm() {
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loadRound();
    }

    void initWidgets() {

        dataModel = new DefaultTableModel(null, columns);

        rankingTable = new JTable();
        rankingTable.setModel(dataModel);
        rankingTable.setBounds((WIDTH - 500) / 2, 70, 500, 500);

        btnMatchUp = new JButton("Xếp lịch");
        btnMatchUp.setBounds(120, 10, 100, 50);
        btnMatchUp.addActionListener(this);

        roundModel = new DefaultComboBoxModel<String>();
        roundBox = new JComboBox<>();
        roundBox.setModel(roundModel);
        roundBox.addActionListener(this);
        roundBox.setBounds(10, 10, 100, 50);

        this.add(rankingTable);
        this.add(roundBox);
    }

    private void loadRound() {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                RoundDAO roundDAO = new RoundDAO();
                listRound = roundDAO.getAllRound();

                roundModel.removeAllElements();
                roundModel.addElement("");
                for (Round i: listRound) {
                    roundModel.addElement("Vòng " + i.getRound());
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox && e.getSource().equals(roundBox)) {
            JComboBox box = (JComboBox) e.getSource();
            int index = box.getSelectedIndex();
            if (index - 1 >= 0 && index - 1 < listRound.size()) {
                loadRanking(listRound.get(index - 1).getRound());
            }
        } else if (e.getSource() instanceof JComboBox && e.getSource().equals(roundBox)) {
            Match m = new Match();
            List<Player> tmp = new ArrayList<>(list);
            Player player1 = tmp.get(new Random().nextInt(tmp.size()));
            tmp.
            Player player2 = tmp.get(new Random().nextInt(tmp.size()));
        }
    }

    private void loadRanking(long round) {
        new Thread(new Runnable(){
        
            @Override
            public void run() {
                RankingDAO rankingDAO = new RankingDAO();
                list = rankingDAO.getRankingByRound(1);
            

                String[][] data = new String[list.size()][7];

                for (int i = 0; i < list.size(); i++) {
                    Ranking player = list.get(i);
                    data[i][0] = "" + player.getId();
                    data[i][1] = "" + player.getName();
                    data[i][2] = "" + player.getBirthYear();
                    data[i][3] = "" + player.getNationality();
                    data[i][4] = "" + player.getPoint();
                    data[i][5] = "" + player.getTotalPointOfCompetitor();
                    data[i][6] = "" + player.getElo();
                }

                dataModel.setDataVector(data, rankColumns);
            }
        }).start();;
    }
    
}