package com.cnpm.chesstournament.views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cnpm.chesstournament.controllers.dao.DAO;
import com.cnpm.chesstournament.controllers.dao.UserDAO;
import com.cnpm.chesstournament.models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends JFrame implements ActionListener {

    private final int HEIGHT = 300;
    private final int WIDTH = 500;

    private JTextField inUsername;
    private JTextField inPassword;
    private JButton btnLogin;

    public LoginFrm() {
        initWidgets();
    }

    public void initWidgets() {

        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        inUsername = new JTextField("Username");
        inPassword= new JTextField("Password");
        btnLogin = new JButton("Login");

        inUsername.setBounds((WIDTH - 200) / 2, 50, 200, 50);
        inPassword.setBounds((WIDTH - 200) / 2, 120, 200, 50);
        btnLogin.setBounds((WIDTH - 200) / 2, 190, 200, 50);
        btnLogin.addActionListener(this);

        this.add(inUsername);
        this.add(inPassword);
        this.add(btnLogin);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if ((e.getSource() instanceof JButton) && (e.getSource().equals(btnLogin))) {
                System.out.println("Click login button!");
                UserDAO userDAO = new UserDAO();

                String username = inUsername.getText();
                String password = inPassword.getText();

                System.out.println(username);
                System.out.println(password);

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                boolean res = userDAO.checkLogin(user);

                if (res) {
                    HomeFrm home = new HomeFrm();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập hoăc mật khẩu sai", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                }

                System.out.println("Result Login: " + res);
            }
    }
    
    public static void main(String[] args) {
        LoginFrm loginFrm = new LoginFrm();
    }
}