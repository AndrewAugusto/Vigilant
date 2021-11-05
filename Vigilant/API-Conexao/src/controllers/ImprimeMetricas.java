package controllers;

import java.util.List;

import models.SelectMaisDemoradasModel;
import models.SelectsChamadas1000xModel;
import models.SelectsMaisDemoradasMediaModel;
import models.TamanhoBancos;
import models.TamanhoTabelasModel;

import models.LoginModel;
import models.SelectConflictsModel;

public class ImprimeMetricas {
	private LoginModel loginModel;

	public ImprimeMetricas(LoginModel loginModel) {
		this.loginModel = loginModel;
	}

	public void imprimirSelectMaisDemoradas() {
		// ** Métricas de Querys Mais Demoradas **
		ObterMetricas obterMetricas = new ObterMetricas(loginModel);
		List<SelectMaisDemoradasModel> selectMaisDemoradas = obterMetricas.SelectMaisDemoradas();
		for (SelectMaisDemoradasModel selectMaisDemoradasModel : selectMaisDemoradas) {
			String query = selectMaisDemoradasModel.getQuery();
			String data = selectMaisDemoradasModel.getData();
			String tempo = selectMaisDemoradasModel.getTempo();
			System.out.printf(query + " | " + tempo + " | " + data);
			System.out.println();
		}
	}

	public void selectsChamadas1000x() {
		// ** Métrica de Selects Chamadas mais de 100 vezes **
		ObterMetricas obterMetricas1 = new ObterMetricas(loginModel);
		List<SelectsChamadas1000xModel> selectsChamadas1000x = obterMetricas1.SelectsChamadas1000x();
		for (SelectsChamadas1000xModel selectsChamadas1000xModel : selectsChamadas1000x) {
			String calls = selectsChamadas1000xModel.getCalls();
			String query = selectsChamadas1000xModel.getQuery();
			String time_exec = selectsChamadas1000xModel.getTotal_exec_time();
			String date = selectsChamadas1000xModel.getDate();
			System.out.println(calls + " | " + query + " | " + time_exec + " | " + date);
		}
	}

	public void selectsMaisDemoradasMedia() {
		// ** Métrica de Média das Selects Mais Demoradas **
		ObterMetricas obterMetricas2 = new ObterMetricas(loginModel);
		List<SelectsMaisDemoradasMediaModel> selectsMaisDemoradasMedia = obterMetricas2.SelectsMaisDemoradasMedia();
		for (SelectsMaisDemoradasMediaModel selectsMaisDemoradasMediaModel : selectsMaisDemoradasMedia) {
			String calls = selectsMaisDemoradasMediaModel.getCalls();
			String date = selectsMaisDemoradasMediaModel.getData();
			String query = selectsMaisDemoradasMediaModel.getQuery();
			String time_avar = selectsMaisDemoradasMediaModel.getTempoMedio();
			System.out.println(calls + " | " + query + " | " + time_avar + " | " + date);
		}
	}

	public void tamanhobancos() {
		// ** Métrica do Tamanho dos Bancos **
		ObterMetricas obterMetricas3 = new ObterMetricas(loginModel);
		List<TamanhoBancos> tamanhobancos1 = obterMetricas3.TamanhoBanco();
		for (TamanhoBancos tamanhobancos : tamanhobancos1) {
			for(int x=1 ; x<5 ; x++) {
			String nome = tamanhobancos.getNome();
			String size = tamanhobancos.getTamanho();
			String date = tamanhobancos.getData();
			System.out.println(nome + " | " + size + " | " + date);
			}
		}
	}

	public void tamanhoTabelas() {
		// ** Métricas de Tamanho das Tabelas **
		ObterMetricas obterMetricas4 = new ObterMetricas(loginModel);
		List<TamanhoTabelasModel> tamanhoTabelas = obterMetricas4.TamanhoTabelas();
		for (TamanhoTabelasModel tamanhoTabelasModel : tamanhoTabelas) {
			String nome = tamanhoTabelasModel.getNome();
			String size = tamanhoTabelasModel.getTamanhoTotal();
			String date = tamanhoTabelasModel.getData();
			System.out.println(nome + " | " + size + " | " + date);
		}
	}
	
	public void conflicts() {
		// ** Métricas de Tamanho das Tabelas **
		ObterMetricas obterMetricas5 = new ObterMetricas(loginModel);
		List<SelectConflictsModel> conflict = obterMetricas5.SelectConflicts();
		for (SelectConflictsModel selectConflictsModel : conflict) {
			String nome = selectConflictsModel.getName();
			String confl = selectConflictsModel.getConfl();
			String confl_dead = selectConflictsModel.getConfl_dead();
			String tempo = selectConflictsModel.getTempo();
			System.out.println(nome + " | " + confl + " | " + confl_dead + " | " + tempo);
		}
	}
}
