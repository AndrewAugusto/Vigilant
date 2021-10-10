package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.LoginModel;
import java.awt.Toolkit;

public class Principal extends JFrame {
	

	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/*
	 * Launch the application.
	 */


	/*
	 * Create the frame.
	 * @param login 
	 */
	public Principal(LoginModel login) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/Vigilant.png")));
		setTitle("Vigilant");
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 570);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.DARK_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnMetricas = new JMenu("M\u00E9tricas");
		mnMetricas.setForeground(Color.WHITE);
		mnMetricas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnMetricas);
		
		JMenuItem mntmTamanhoBancos = new JMenuItem("Tamanho dos Bancos");
		mntmTamanhoBancos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmTamanhoBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TamanhoBancosView tela = new TamanhoBancosView(login);
				tela.setVisible(true);
				dispose();
				
				
			}
		});
		mnMetricas.add(mntmTamanhoBancos);
		
		
		JMenuItem mntmTamanhoTabelas = new JMenuItem("Tamanho Tabelas");
		mntmTamanhoTabelas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmTamanhoTabelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TamanhoTabelasView tela = new TamanhoTabelasView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmTamanhoTabelas);
		
		
		JMenuItem mntmInstrucoes1000x = new JMenuItem("Instru\u00E7\u00F5es Chamadas Mais de 100x");
		mntmInstrucoes1000x.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmInstrucoes1000x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsChamadas1000xView tela = new SelectsChamadas1000xView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInstrucoes1000x);
		
		
		JMenuItem mntmIstrucoesMaisDemoradas = new JMenuItem("Instru\u00E7\u00F5es Mais Demoradas");
		mntmIstrucoesMaisDemoradas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmIstrucoesMaisDemoradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsMaisDemoradasView tela = new SelectsMaisDemoradasView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmIstrucoesMaisDemoradas);
		
		
		JMenuItem mntmInstrucoesMaisDemoradasMedia = new JMenuItem("Instru\u00E7\u00F5es Mais Demoradas Em Media");
		mntmInstrucoesMaisDemoradasMedia.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmInstrucoesMaisDemoradasMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsMaisDemoradasMediaView tela = new SelectsMaisDemoradasMediaView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInstrucoesMaisDemoradasMedia);
		
		
		JMenuItem mntmInstrucoesdeConflitoNaDatabase = new JMenuItem("Instru\u00E7\u00F5es Conflitos na Database");
		mntmInstrucoesdeConflitoNaDatabase.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmInstrucoesdeConflitoNaDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsDatabaseConflictView tela = new SelectsDatabaseConflictView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInstrucoesdeConflitoNaDatabase);
		
		
		JMenuItem mntmFalhasdearquivos = new JMenuItem("Contagem de Tranferências de Arquivos");
		mntmFalhasdearquivos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmFalhasdearquivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsArchiverView tela = new SelectsArchiverView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmFalhasdearquivos);
		
		
		JMenuItem mntmInfosDataBase = new JMenuItem("Informações Gerais da Database");
		mntmInfosDataBase.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmInfosDataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsInfosDbView tela = new SelectsInfosDbView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmInfosDataBase);
		
		
		JMenuItem mntmDbcommit = new JMenuItem("Commits no Database");
		mntmDbcommit.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDbcommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectsDbcommitView tela = new SelectsDbcommitView(login);
				tela.setVisible(true);
				dispose();
			}
		});
		mnMetricas.add(mntmDbcommit);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setForeground(Color.WHITE);
		mnSair.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
		mnSair.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/bin/Images/necto.png")));
		lblNewLabel.setBounds(264, 112, 370, 271);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
}
