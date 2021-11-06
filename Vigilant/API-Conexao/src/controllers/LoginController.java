package controllers;

import java.sql.Connection;
import java.util.Scanner;

import models.LoginModel;

public class LoginController {
	
	public Connection con;
	public Connection in;
	
	//Conexao do Postgres
	public LoginController(LoginModel login) {
		login.getUsuario();
		login.getSenha();
		login.getBanco();
		login.getPorta();
	}

	public static LoginModel PreencherLogin() {
		@SuppressWarnings("resource")
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