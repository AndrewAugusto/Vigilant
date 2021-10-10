package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//import com.sun.tools.example.debug.expr.ExpressionParser.GetFrame;

import controllers.LoginController;
import models.LoginModel;
import java.awt.Toolkit;

public class TelaRegister extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfPor;
	private JTextField txfDat;
	private JTextField txfUser;
	private JTextField txfPass;

	/*
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
	
	/*
	 * Create the frame.
	 */
	public TelaRegister() {
		setTitle("Vigilant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRegister.class.getResource("/img/Vigilant.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(63, 36, 51, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDatabase = new JLabel("DATABASE:");
		lblDatabase.setForeground(Color.WHITE);
		lblDatabase.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblDatabase.setBounds(37, 72, 77, 14);
		panel.add(lblDatabase);
		
		JLabel lblUsurio = new JLabel("USUÁRIO:");
		lblUsurio.setForeground(Color.WHITE);
		lblUsurio.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblUsurio.setBounds(48, 107, 66, 14);
		panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblSenha.setBounds(63, 142, 51, 14);
		panel.add(lblSenha);
		
		txfPor = new JTextField();
		txfPor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txfPor.setBackground(Color.LIGHT_GRAY);
		txfPor.setBounds(125, 35, 190, 20);
		panel.add(txfPor);
		txfPor.setColumns(10);
		
		txfDat = new JTextField();
		txfDat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txfDat.setColumns(10);
		txfDat.setBackground(Color.LIGHT_GRAY);
		txfDat.setBounds(124, 71, 190, 20);
		panel.add(txfDat);
		
		txfUser = new JTextField();
		txfUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txfUser.setColumns(10);
		txfUser.setBackground(Color.LIGHT_GRAY);
		txfUser.setBounds(125, 106, 190, 20);
		panel.add(txfUser);
		
		txfPass = new JTextField();
		txfPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txfPass.setColumns(10);
		txfPass.setBackground(Color.LIGHT_GRAY);
		txfPass.setBounds(125, 141, 190, 20);
		panel.add(txfPass);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaRegister.class.getResource("/img/necto.png")));
		lblNewLabel_1.setBounds(10, 282, 340, 146);
		panel.add(lblNewLabel_1);
		
		JButton btnSub = new JButton("CONECTAR");
		btnSub.setForeground(new Color(0, 100, 0));
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
					
					JButton btnSubmit = (JButton)e.getSource();
					TelaRegister telaLogin = findParent(btnSubmit.getParent(), TelaRegister.class);
					telaLogin.setVisible(false);
					telaLogin.dispose();
				}	
				
			}
		});
		
		btnSub.setBackground(Color.LIGHT_GRAY);
		btnSub.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnSub.setBounds(81, 182, 234, 43);
		panel.add(btnSub);
	}
	
	public static <T extends Container> T findParent(Component componente, Class<T> nomeClasse)  {
      if (componente == null)
         return null;
      if (nomeClasse.isInstance(componente))
         return (nomeClasse.cast(componente));
      else
         return findParent(componente.getParent(), nomeClasse);     
      
   }
	
	protected static void exit(int i) {	
		
	}	
}