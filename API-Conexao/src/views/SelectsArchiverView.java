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
import models.SelectArchiverModel;
import java.awt.Toolkit;


public class SelectsArchiverView extends JFrame {
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
	
	public SelectsArchiverView(LoginModel login) {
		setTitle("Vigilant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectsArchiverView.class.getResource("/img/Vigilant.png")));
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
				"Contagem de Arquivos","Contagem de Falhas", "Ultima Falha","Data"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnMostrarTamanhoBancos = new JButton("Mostrar Dados");
		btnMostrarTamanhoBancos.setBackground(Color.BLACK);
		btnMostrarTamanhoBancos.setForeground(Color.WHITE);
		btnMostrarTamanhoBancos.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnMostrarTamanhoBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SelectArchiverModel> lista = null;
				
				ObterMetricas metricas = new ObterMetricas(login);
				
				lista = metricas.SelectArchiver();
				
				String countarchived;
				String countfailed;
				String lastfailed;
				String data;
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				//Remove as linhas da pesquisa anterior
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
				
				//Adiciona as linhas do array retornado na tabela
				for(int i = 1; i < lista.size(); i++)
				{		
					countarchived = lista.get(i).getCountarchived();
					countfailed = lista.get(i).getCountfailed();
					lastfailed = lista.get(i).getLastfailed();
					data = lista.get(i).getData();
					
					model.addRow(new String[] {countarchived,countfailed,lastfailed,data});
				}
			}
		});
		panel.add(btnMostrarTamanhoBancos);
		
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
