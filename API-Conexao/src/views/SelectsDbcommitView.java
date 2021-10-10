package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ObterMetricas;
import models.LoginModel;
import models.SelectDbcommitModel;
import java.awt.Toolkit;


public class SelectsDbcommitView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */

	/*
	 * Create the frame.
	 * @param login 
	 */
	
	public SelectsDbcommitView(LoginModel login) {
		setTitle("Vigilant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectsDbcommitView.class.getResource("/img/Vigilant.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data Name","Commit","RollBack","Disk Block", "Buffers Hit", "Conflitos", "DeadLocks","Data"
				}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnDbcommit = new JButton("Mostrar Dados");
		btnDbcommit.setBackground(Color.BLACK);
		btnDbcommit.setForeground(Color.WHITE);
		btnDbcommit.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnDbcommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SelectDbcommitModel> lista = null;
				
				ObterMetricas metricas = new ObterMetricas(login);
				
				lista = metricas.SelectDbcommit();
				
				String Commit;
				String Db_name;
				String Rollback;
				String Disk_block;
				String Buffers_hit;
				String Conflitos;
				String Deadlocks;
				String Data;
				
				
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				//Remove as linhas da pesquisa anterior
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
				
				//Adiciona as linhas do array retornado na tabela
				for(int i = 1; i < lista.size(); i++)
				{		
					Db_name = lista.get(i).getDbname();
					Commit = lista.get(i).getCommit();
					Rollback = lista.get(i).getRollback();
					Disk_block = lista.get(i).getBlkread();
					Buffers_hit = lista.get(i).getBlkhit();
					Conflitos = lista.get(i).getConflicts();
					Deadlocks = lista.get(i).getDeadlocks();
					Data = lista.get(i).getData();
					
					model.addRow(new String[] {Db_name, Commit, Rollback, Disk_block, Buffers_hit, Conflitos, Deadlocks,Data});
				}
			}
		});
		panel.add(btnDbcommit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal telaPrinc = new Principal(login);
				telaPrinc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(TamanhoBancosView.class.getResource("/img/seta-voltar.png")));
		btnNewButton.setPreferredSize(new Dimension(60, 23));
		btnNewButton.setMaximumSize(new Dimension(50, 23));
		panel_1.add(btnNewButton);
	}

}
