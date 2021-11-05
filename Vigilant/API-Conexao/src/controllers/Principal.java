package controllers;

import models.LoginModel;

public class Principal {

	public static void main(String[] args) {
		LoginModel loginModel = LoginController.PreencherLogin();
		Menu menu = new Menu(loginModel);
		menu.startmenu();
	}
}
