package com.cnpm.chesstournament.views;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JButton btnManagePlayer;
    private JButton btnUpdateMatchResult;
    private JButton btnRanking;
    private JButton btnMatchUp;
    private JButton btnEloStat;

    public HomeFrm() {

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        btnManagePlayer = new JButton("Quản lí kì thủ");
        btnManagePlayer.setBounds((WIDTH - 200) / 2, 100, 200, 50);
        btnManagePlayer.addActionListener(this);

        btnUpdateMatchResult = new JButton("Cập nhật kết quả");
        btnUpdateMatchResult.setBounds((WIDTH - 200) / 2, 170, 200, 50);
        btnUpdateMatchResult.addActionListener(this);

        btnRanking = new JButton("Xếp hạng");
        btnRanking.setBounds((WIDTH - 200) / 2, 240, 200, 50);
        btnRanking.addActionListener(this);

        btnMatchUp = new JButton("Xếp cặp thi đấu");
        btnMatchUp.setBounds((WIDTH - 200) / 2, 310, 200, 50);
        btnMatchUp.addActionListener(this);

        btnEloStat = new JButton("Thống kê Elo");
        btnEloStat.setBounds((WIDTH - 200) / 2, 380, 200, 50);
        btnEloStat.addActionListener(this);

        this.add(btnEloStat);
        this.add(btnManagePlayer);
        this.add(btnMatchUp);
        this.add(btnUpdateMatchResult);
        this.add(btnRanking);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       if (source instanceof JButton) {
           if (source.equals(btnEloStat)) {
                EloStatFrm eloStatFrm = new EloStatFrm();
           } else if (source.equals(btnManagePlayer)) {
                PlayerManagerFrm playerManagerFrm = new PlayerManagerFrm();
           } else if (source.equals(btnRanking)) {
                RankingFrm ranking = new RankingFrm();
           } else if (source.equals(btnMatchUp)) {
                MatchUpFrm matchUpFrm = new MatchUpFrm();
           } else if (source.equals(btnUpdateMatchResult)) {

           }
       }
    }
    
}