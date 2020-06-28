package com.cnpm.chesstournament.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cnpm.chesstournament.controllers.dao.HappendMatchDAO;
import com.cnpm.chesstournament.models.HappenedMatch;
import com.cnpm.chesstournament.models.Match;

public class UpdateResultFrm extends JFrame implements ActionListener {


    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JLabel lablePlayer1;
    private JLabel lablePlayer2;
    private JLabel namePlayer1;
    private JLabel namePlayer2;
    private JTextField pointPlayer1;
    private JTextField pointPlayer2;
    private JTextField eloPlayer1;
    private JTextField eloPlayer2;
    private JButton btnOK;
    private JButton btnCancel;


    private Match match;

    public UpdateResultFrm(Match match) {

        this.match = match;
        
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    void initWidgets() {

        lablePlayer1 = new JLabel("Người chơi 1");
        lablePlayer2 = new JLabel("Người chơi 2");
        namePlayer1 = new JLabel(match.getPlayer1().getName());
        namePlayer2 = new JLabel(match.getPlayer2().getName());
        pointPlayer1 = new JTextField("Point");
        pointPlayer2 = new JTextField("Point");
        eloPlayer1 = new JTextField("Elo");
        eloPlayer2 = new JTextField("Elo");

        lablePlayer1.setBounds(10, 10, 200, 50);
        lablePlayer2.setBounds(220, 10, 200, 50);
        namePlayer1.setBounds(10, 80, 200, 50);
        namePlayer2.setBounds(220, 80, 200, 50);
        pointPlayer1.setBounds(10, 150, 200, 50);
        pointPlayer2.setBounds(220, 150, 200, 50);
        eloPlayer1.setBounds(10, 220, 200, 50);
        eloPlayer2.setBounds(220, 220, 200, 50);

        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(10, 400, 100, 50);
        btnOK.setBounds(120, 400, 100, 50);
        btnOK.addActionListener(this);
        btnCancel.addActionListener(this);

        this.add(lablePlayer1);
        this.add(lablePlayer2);
        this.add(namePlayer1);
        this.add(namePlayer2);
        this.add(pointPlayer1);
        this.add(pointPlayer2);
        this.add(eloPlayer1);
        this.add(eloPlayer2);
        this.add(btnOK);
        this.add(btnCancel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnCancel))) {
            this.dispose();
        } else if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnOK))) {
            new Thread(new Runnable(){
            
                @Override
                public void run() {
                    System.out.println("Ok");
                    HappendMatchDAO happendMatchDAO = new HappendMatchDAO();
            
                    long point1 = Long.parseLong(pointPlayer1.getText().trim());
                    long elo1 = Long.parseLong(eloPlayer1.getText().trim());
                    HappenedMatch happenedMatch1 = new HappenedMatch();
                    happenedMatch1.setMatch(match);
                    happenedMatch1.setElo(elo1);
                    happenedMatch1.setPoint(point1);
                    happenedMatch1.setPlayer(match.getPlayer1());

                    long point2 = Long.parseLong(pointPlayer2.getText().trim());
                    long elo2 = Long.parseLong(eloPlayer2.getText().trim());
                    HappenedMatch happenedMatch2 = new HappenedMatch();
                    happenedMatch2.setMatch(match);
                    happenedMatch2.setElo(elo2);
                    happenedMatch2.setPoint(point2);
                    happenedMatch2.setPlayer(match.getPlayer2());

                    boolean res = happendMatchDAO.createHappenedMatch(happenedMatch1, happenedMatch2);
                    System.out.println("Res: " + res);
                    if (res) {
                        dispose();
                    }
                }
            }).start();
        }
    }
    
}