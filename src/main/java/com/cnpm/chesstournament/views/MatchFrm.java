package com.cnpm.chesstournament.views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cnpm.chesstournament.controllers.dao.MatchDAO;
import com.cnpm.chesstournament.controllers.dao.RoundDAO;
import com.cnpm.chesstournament.models.Match;
import com.cnpm.chesstournament.models.Round;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MatchFrm extends JFrame implements ActionListener{

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JComboBox roundBox;
    private JTable matchTable;
    private DefaultTableModel dataModel;
    private DefaultComboBoxModel roundModel;
    private String[] columns = {"ID", "Ten nguoi choi 1", "Ten nguoi choi 2"};
    private List<Round> listRound;
    private List<Match> list;
    private JScrollPane pane;

    public MatchFrm() {

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loadRound();
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

    void initWidgets() {

        dataModel = new DefaultTableModel(null, columns);
        matchTable = new JTable();
        matchTable.setModel(dataModel);
        matchTable.setBounds((WIDTH - 500) / 2, 70, 500, 500);
        pane = new JScrollPane();
        pane.setViewportView(matchTable);
        pane.setBounds((WIDTH - 500) / 2, 70, 500, 500);
        matchTable.getTableHeader().setBackground(Color.WHITE);

        roundModel = new DefaultComboBoxModel<String>();
        roundBox = new JComboBox<>();
        roundBox.setModel(roundModel);
        roundBox.addActionListener(this);
        roundBox.setBounds(10, 10, 100, 50);

        matchTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = matchTable.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / matchTable.getRowHeight(); 
				if (row < matchTable.getRowCount() && row >= 0 && column < matchTable.getColumnCount() && column >= 0) {
					new UpdateResultFrm(list.get(row));
				}
			}
		});

        this.add(pane);
        this.add(roundBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox && e.getSource().equals(roundBox)) {
            JComboBox box = (JComboBox) e.getSource();
            int index = box.getSelectedIndex();
            if (index - 1 >= 0 && index - 1 < listRound.size()) {
                loadMatch(listRound.get(index - 1).getRound());
            }
        }
    }

    private void loadMatch(long roundIndex) {

        new Thread(new Runnable(){
        
            @Override
            public void run() {
                MatchDAO matchDAO = new MatchDAO();

                list = matchDAO.getAllMatchByRound(roundIndex);
                final String[][] data = new String[list.size()][3];

                for (int i = 0; i < list.size(); i++) {
                    final Match match = list.get(i);
                    data[i][0] = "" + match.getId();
                    data[i][1] = "" + match.getPlayer1().getName();
                    data[i][2] = "" + match.getPlayer2().getName();
                }

                dataModel.setDataVector(data, columns);
                dataModel.fireTableDataChanged();
            }
        }).start();
    }
    
}