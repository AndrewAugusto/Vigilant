package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import models.LoginModel;

public class LoginController {
	
	private String driver = "org.postgresql.Driver";
	private String caminho;
	private String porta;
	private String banco;
	private String usuario;
	private String senha;
	private String caminholite;
	public Connection con;
	public Connection in;
	
	//Conexao do Postgres
	public LoginController(LoginModel login) {
		usuario = login.getUsuario();
		senha = login.getSenha();
		banco = login.getBanco();
		porta = login.getPorta();

		//Postgres
		caminho = "jdbc:postgresql://localhost:" + porta + "/" + banco;
		
		//SQLite
		caminholite = "jdbc:sqlite:banco_de_dados/banco_sqlite.db";
	}

	public static LoginModel PreencherLogin() {
		Scanner t = new Scanner(System.in);
		System.out.println("Usuário: ");
		String usuario = t.nextLine();
		System.out.println("Senha: ");
		String senha = t.nextLine();
		System.out.println("Banco: ");
		String banco = t.nextLine();
		System.out.println("Porta: ");
		String porta = t.nextLine();

		LoginModel loginModel = new LoginModel();
		loginModel.setBanco(banco);
		loginModel.setPorta(porta);
		loginModel.setSenha(senha);
		loginModel.setUsuario(usuario);
		return loginModel;
	}
}