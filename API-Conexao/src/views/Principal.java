package views;

import java.awt.BorderLayout;
import controllers.Conexao;
import controllers.ObterMetricas;
import models.TamanhoBancos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {
	
	ObterMetricas metricas = new ObterMetricas();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 424);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMetricas = new JMenu("M\u00E9tricas");
		menuBar.add(mnMetricas);
		
		JMenuItem mntmTamanhoBancos = new JMenuItem("Tamanho dos Bancos");
		mntmTamanhoBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TamanhoBancosView tela = new TamanhoBancosView();
				tela.setVisible(true);
				dispose();
				
				
			}
		});
		mnMetricas.add(mntmTamanhoBancos);
		
		JMenuItem mntmTamanhoTabelas = new JMenuItem("Tamanho Tabelas");
		mntmTamanhoTabelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TamanhoTabelasView tela = new TamanhoTabelasView();
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmTamanhoTabelas);
		
		JMenuItem mntmInstrucoes1000x = new JMenuItem("Instru\u00E7\u00F5es Chamadas Mais de 1000x");
		mntmInstrucoes1000x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsChamadas1000xView tela = new SelectsChamadas1000xView();
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInstrucoes1000x);
		
		JMenuItem mntmIstrucoesMaisDemoradas = new JMenuItem("Instru\u00E7\u00F5es Mais Demoradas");
		mntmIstrucoesMaisDemoradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsMaisDemoradasView tela = new SelectsMaisDemoradasView();
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmIstrucoesMaisDemoradas);
		
		JMenuItem mntmInstrucoesMaisDemoradasMedia = new JMenuItem("Instru\u00E7\u00F5es Mais Demoradas Em Media");
		mntmInstrucoesMaisDemoradasMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsMaisDemoradasMediaView tela = new SelectsMaisDemoradasMediaView();
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInstrucoesMaisDemoradasMedia);
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnSair.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
}
