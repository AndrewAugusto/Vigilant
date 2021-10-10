package views;

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
import models.SelectsChamadas1000xModel;
import java.awt.Toolkit;

public class SelectsChamadas1000xView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	/*
	 * Launch the application.
	 */

	/*
	 * Create the frame.
	 * @param login 
	 */
	
	public SelectsChamadas1000xView(LoginModel login) {
		setTitle("Vigilant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectsChamadas1000xView.class.getResource("/img/Vigilant.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 49, 824, 349);
		contentPane.add(scrollPane);
		
		table = new JTable();
	
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data", "Chamadas", "Query", "Tempo de execu\u00E7\u00E3o total"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(144);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 409, 824, 41);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		
		JButton btnMostrarTamanhoBancos = new JButton("Mostrar Dados");
		btnMostrarTamanhoBancos.setForeground(Color.WHITE);
		btnMostrarTamanhoBancos.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnMostrarTamanhoBancos.setBackground(Color.BLACK);
		btnMostrarTamanhoBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SelectsChamadas1000xModel> lista = new ArrayList<SelectsChamadas1000xModel>();
				ObterMetricas metricas = new ObterMetricas(login);
				lista = metricas.SelectsChamadas1000x();
				
				String calls;
				String query;
				String tempo;
				String data;
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				//Remove as linhas da pesquisa anterior
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
				
				//Adiciona as linhas do array retornado na tabela
				for(int i = 0; i < lista.size(); i++)
				{		
					data = lista.get(i).getDate();
					calls = lista.get(i).getCalls();
					query = lista.get(i).getQuery();
					tempo = lista.get(i).getTotal_exec_time();
					
					model.addRow(new String[] {data,calls, query,tempo});
				}
			}
		});
		panel.add(btnMostrarTamanhoBancos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 824, 33);
		panel_1.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(Color.BLACK);
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
