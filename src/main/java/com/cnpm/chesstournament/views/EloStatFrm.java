package com.cnpm.chesstournament.views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.cnpm.chesstournament.controllers.dao.EloStatDAO;
import com.cnpm.chesstournament.models.EloStat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EloStatFrm extends JFrame implements ActionListener {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTable eloTable;
    private DefaultTableModel dataModel;
    private final String[] colums = { "ID", "Tên", "Elo cũ", "Elo mới", "Elo tăng giảm" };
    private List<EloStat> list;
    private JScrollPane pane;

    public EloStatFrm() {

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);

        initWidgets();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loadData();
    }

    void initWidgets() {
        dataModel = new DefaultTableModel(null, colums);

        eloTable = new JTable();
        eloTable.setModel(dataModel);
        eloTable.setBounds((WIDTH - 500) / 2, 10, 500, 500);

        pane = new JScrollPane();
        pane.setBounds((WIDTH - 500) / 2, 70, 500, 500);
        pane.setViewportView(eloTable);
        eloTable.getTableHeader().setBackground(Color.WHITE);


        eloTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = eloTable.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / eloTable.getRowHeight(); 
				if (row < eloTable.getRowCount() && row >= 0 && column < eloTable.getColumnCount() && column >= 0) {
					new DetailFrm(list.get(row));
				}
			}
		});

        this.add(pane);
    }

    void loadData() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                final EloStatDAO eloStatDAO = new EloStatDAO();
                list = eloStatDAO.getAllEloStat();
                final String[][] data = new String[list.size()][5];

                for (int i = 0; i < list.size(); i++) {
                    final EloStat player = list.get(i);
                    data[i][0] = "" + player.getId();
                    data[i][1] = "" + player.getName();
                    data[i][2] = "" + player.getOldElo();
                    data[i][3] = "" + player.getNewElo();
                    data[i][4] = "" + player.getEloChange();
                }

                dataModel.setDataVector(data, colums);
                dataModel.fireTableDataChanged();
            }
        }).start();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        
    }
    
}