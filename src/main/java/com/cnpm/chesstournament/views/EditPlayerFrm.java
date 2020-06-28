package com.cnpm.chesstournament.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.cnpm.chesstournament.controllers.dao.PlayerDAO;
import com.cnpm.chesstournament.models.Player;

public class EditPlayerFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private Player player;
    private JTextField edtName;
    private JTextField editNationality;
    private JTextField edtBirthYear;
    private JButton btnOk;
    private JButton btnCancel;

    public EditPlayerFrm(Player player) {
        this.player = player;

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initWidgets() {

        edtName = new JTextField(player.getName());
        edtBirthYear = new JTextField("" + player.getBirthYear());
        editNationality = new JTextField(player.getNationality());

        edtName.setBounds(10, 10, 200, 50);
        edtBirthYear.setBounds(10, 70, 200, 50);
        editNationality.setBounds(10, 130, 200, 50);

        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");

        btnCancel.setBounds(10, 200, 100, 50);
        btnOk.setBounds(120, 200, 100, 50);
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);

        this.add(edtName);
        this.add(edtBirthYear);
        this.add(editNationality);
        this.add(btnCancel);
        this.add(btnOk);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnCancel))) {
            this.dispose();
        } else if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnOk))) {
            String name = edtName.getText().trim();
            long birthYear = Long.parseLong(edtBirthYear.getText().trim());
            String nationality = editNationality.getText().trim();
            
            player.setName(name);
            player.setBirthYear(birthYear);
            player.setNationality(nationality);

            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.updatePlayer(player);
            this.dispose();
        }
    }
}