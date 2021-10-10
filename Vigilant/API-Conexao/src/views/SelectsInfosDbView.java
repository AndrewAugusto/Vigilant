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
import models.SelectInfosDbModel;
import java.awt.Toolkit;


public class SelectsInfosDbView extends JFrame {
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
	
	public SelectsInfosDbView(LoginModel login) {
		setTitle("Vigilant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectsInfosDbView.class.getResource("/img/Vigilant.png")));
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
				"Id","Db_Name", "Processo","Usuario", "Aplicação", "Ip", "Host","Port", "Inicio da Query","Tipo de Evento","Esperando Evento","Stats","Querys","Out type","Data"  
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnInformacoesgerais = new JButton("Mostrar Dados");
		btnInformacoesgerais.setBackground(Color.BLACK);
		btnInformacoesgerais.setForeground(Color.WHITE);
		btnInformacoesgerais.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnInformacoesgerais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<SelectInfosDbModel> lista = null;
				
				ObterMetricas metricas = new ObterMetricas(login);
				
				lista = metricas.SelectInfosDb();
				
				String Id;
				String Db_name;
				String Processo;
				String Usuario;
				String Aplicacao;
				String Ip;
				String Host;
				String Port;
				String Iniciodaquery;
				String Tipodeevento;
				String Esperandoevento;
				String Stats;
				String Querys;
				String Outtype;
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
					Id = lista.get(i).getDbid();
					Db_name = lista.get(i).getDbname();
					Processo = lista.get(i).getProcess();
					Usuario = lista.get(i).getUsername();
					Aplicacao = lista.get(i).getConect();
					Ip = lista.get(i).getIp();
					Host = lista.get(i).getHost();
					Port = lista.get(i).getPort();
					Iniciodaquery = lista.get(i).getStartquery();
					Tipodeevento = lista.get(i).getTypevent();
					Esperandoevento = lista.get(i).getWaitevent();
					Stats = lista.get(i).getStats();
					Querys = lista.get(i).getQuery();
					Outtype = lista.get(i).getOutype();
					Data = lista.get(i).getData();
					
					model.addRow(new String[] {Id, Db_name, Processo, Usuario, Aplicacao, Ip, Host, Port, Iniciodaquery, Tipodeevento, Esperandoevento, Stats, Querys, Outtype, Data});
				}
			}
		});
		panel.add(btnInformacoesgerais);
		
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
