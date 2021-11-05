package controllers;

import models.LoginModel;
import controllers.Leitor;

public class Menu {
	
	ImprimeMetricas imprimeMetricas;
	public Menu(LoginModel loginModel) {
		this.imprimeMetricas=new ImprimeMetricas(loginModel);
	}
	
	public void startmenu() {
		
		int choice = 1;
		
		while (choice !=0){ 
			choice = controle ();
			
			switch (choice) {
			
				case 1:
					imprimeMetricas.tamanhobancos();
					break;
				case 2:
					imprimeMetricas.tamanhoTabelas();
					break;
				case 3:
					imprimeMetricas.selectsChamadas1000x();
					break;
				case 4:
					imprimeMetricas.imprimirSelectMaisDemoradas();
					break;
				case 5:
					imprimeMetricas.selectsMaisDemoradasMedia();
					break;
				
				}
			
			}
		System.out.println("\nDeseja fechar o programa?");
		System.out.println("[1] Sim");
		System.out.println("[2] Não");
		System.out.print("Opção: ");

		Leitor leitor = new Leitor();
		int opcao = leitor.getValor();

		if (opcao == 1) {
			System.out.println("\n programa encerrado..");
			System.out.println("----------------------------");
			System.exit(0);
		}
	}

	public int continuar() {
		System.out.println("\n Realizar outra consulta?");
		System.out.println("[1] Sim");
		System.out.println("[2] Não");
		System.out.print("Opção: ");

		Leitor leitor = new Leitor();
		int opcao = leitor.getValor();

		if (opcao == 2) {
			fecharPrograma();
		}
		return opcao;
	}

	public void fecharPrograma() {
		System.out.println("\nDeseja fechar o programa?");
		System.out.println("[1] Sim");
		System.out.println("[2] Não");
		System.out.print("Opção: ");

		Leitor leitor = new Leitor();
		int opcao = leitor.getValor();

		if (opcao == 1) {
			System.out.println("\nO programa foi encerrado..");
			System.out.println("----------------------------");
			System.exit(0);
		}
	}
	public int controle() {
	System.out.println("\n-------- Métricas --------");
	System.out.println(" [1] - Tamanho do Banco");
	System.out.println(" [2] - Tamanho das Tabelas");
	System.out.println(" [3] - Mais Chamadas");
	System.out.println(" [4] - Mais Demoradas");
	System.out.println(" [5] - Média de Demoras");
	System.out.println(" [0] - Sair");
	System.out.print("Opção: ");
	
	Leitor leitor = new Leitor();
	int choice = leitor.getValor();
	return choice;
	
	}
}
