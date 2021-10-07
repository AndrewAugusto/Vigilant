package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.LoginController;
import models.LoginModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRegister extends JFrame {

	private JPanel contentPane;
	private JTextField txfPor;
	private JTextField txfDat;
	private JTextField txfUser;
	private JTextField txfPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRegister frame = new TelaRegister();
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
	public TelaRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 11, 360, 439);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PORTA:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 31, 61, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDatabase = new JLabel("DATABASE:");
		lblDatabase.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDatabase.setBounds(10, 67, 95, 14);
		panel.add(lblDatabase);
		
		JLabel lblUsurio = new JLabel("USUÃ�RIO:");
		lblUsurio.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsurio.setBounds(10, 102, 84, 14);
		panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSenha.setBounds(10, 137, 61, 14);
		panel.add(lblSenha);
		
		txfPor = new JTextField();
		txfPor.setBackground(Color.LIGHT_GRAY);
		txfPor.setBounds(116, 29, 190, 20);
		panel.add(txfPor);
		txfPor.setColumns(10);
		
		txfDat = new JTextField();
		txfDat.setColumns(10);
		txfDat.setBackground(Color.LIGHT_GRAY);
		txfDat.setBounds(115, 65, 190, 20);
		panel.add(txfDat);
		
		txfUser = new JTextField();
		txfUser.setColumns(10);
		txfUser.setBackground(Color.LIGHT_GRAY);
		txfUser.setBounds(116, 100, 190, 20);
		panel.add(txfUser);
		
		txfPass = new JTextField();
		txfPass.setColumns(10);
		txfPass.setBackground(Color.LIGHT_GRAY);
		txfPass.setBounds(116, 135, 190, 20);
		panel.add(txfPass);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaRegister.class.getResource("/img/necto.png")));
		lblNewLabel_1.setBounds(10, 282, 340, 146);
		panel.add(lblNewLabel_1);
		
		JButton btnSub = new JButton("SUBMIT");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginModel login = new LoginModel();
				
				login.setBanco(txfDat.getText());
				login.setPorta(txfPor.getText());
				login.setUsuario(txfUser.getText());
				login.setSenha(txfPass.getText());
				
				LoginController lc = new LoginController(login);
				
				if(lc.iniciarConexao()) {
					Principal telaPrinc = new Principal(login);
					telaPrinc.setVisible(true);
				}
			}
		});
		btnSub.setBackground(Color.LIGHT_GRAY);
		btnSub.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSub.setBounds(10, 176, 340, 40);
		panel.add(btnSub);
	}
}
