package controllers;

import models.LoginModel;

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
				case 6:
					imprimeMetricas.conflicts();
				
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
		else {
			startmenu();
		} 			
	}
 
	public int controle() {
	System.out.println("\n-------- Métricas --------");
	System.out.println(" [1] - Tamanho do Banco");
	System.out.println(" [2] - Tamanho das Tabelas");
	System.out.println(" [3] - Queries Mais Chamadas");
	System.out.println(" [4] - Queries Mais Demoradas");
	System.out.println(" [5] - Média de Queries Demoradas");
	System.out.println(" [6] - Conflitos no Banco de Dados");
	System.out.println(" [0] - Sair");
	System.out.print("Opção: ");
	
	Leitor leitor = new Leitor();
	int choice = leitor.getValor();
	return choice;
	
	}
}