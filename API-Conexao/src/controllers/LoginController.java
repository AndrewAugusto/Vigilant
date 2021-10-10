package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.LoginModel;

public class LoginController {
	private String driver = "org.postgresql.Driver";
	private String caminho;
	private String porta;
	private String banco;
	private String usuario;
	private String senha;
	public Connection  con;
	
	
	public LoginController(LoginModel login) {
		usuario = login.getUsuario();
		senha = login.getSenha();
		banco = login.getBanco();
		porta = login.getPorta();
		
		caminho = "jdbc:postgresql://localhost:" + porta + "/" + banco;
		
		
	}


	public Boolean iniciarConexao() {
		// Necess�rio alterar a url, usuario e senha para o banco que ser� conectado
		try {
			System.setProperty("jdbc.Drivers",driver );
			con = DriverManager.getConnection(caminho,usuario,senha);
				//JOptionPane.showMessageDialog(null, "Conex�o efetuada com sucesso!");
			return true;
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro de conex�o: \n"+ex.getMessage());
			return false;
		}

	}
}
