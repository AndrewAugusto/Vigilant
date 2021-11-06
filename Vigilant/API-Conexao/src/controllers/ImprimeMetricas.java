package controllers;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.SelectMaisDemoradasModel;
import models.SelectsChamadas1000xModel;
import models.SelectsMaisDemoradasMediaModel;
import models.TamanhoBancos;
import models.TamanhoTabelasModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import models.CaminhoExcel;
import models.LoginModel;
import models.SelectConflictsModel;

public class ImprimeMetricas {
	private LoginModel loginModel;
	CaminhoExcel caminho = new CaminhoExcel();
	
	public ImprimeMetricas(LoginModel loginModel) {
		this.loginModel = loginModel;
	}
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void imprimirSelectMaisDemoradas() {
		// ** Métricas de Querys Mais Demoradas **
		ObterMetricas obterMetricas = new ObterMetricas(loginModel);
		List<SelectMaisDemoradasModel> selectMaisDemoradas = obterMetricas.SelectMaisDemoradas();
		
		//Excel
				int contColunmExcel = 0;
				String dataAtual = getDateTime();
				String filename = caminho.getNome() + "SelectsMaisDemoradas"+ dataAtual + ".xls";
		            @SuppressWarnings("resource")
					HSSFWorkbook workbook=new HSSFWorkbook();
		            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		       //Excel 
		
		for (SelectMaisDemoradasModel selectMaisDemoradasModel : selectMaisDemoradas) {
			String query = selectMaisDemoradasModel.getQuery();
			String data = selectMaisDemoradasModel.getData();
			String tempo = selectMaisDemoradasModel.getTempo();
			System.out.printf(query + " | " + tempo + " | " + data);
			System.out.println();
			
			//Excel
            // criando as linhas
            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Query");
            rowhead.createCell(1).setCellValue("Data");
            rowhead.createCell(2).setCellValue("Tempo");
            
		//adiciona os dados no excel
			contColunmExcel +=1;
	            // local do arquivo
	           
	            // definindo seus valores
	            // por exemplo protocolo.getProtocolo();
	            HSSFRow row=   sheet.createRow((short)contColunmExcel);
	            row.createCell(0).setCellValue(query);
	            row.createCell(1).setCellValue(data);
	            row.createCell(2).setCellValue(tempo);
	    //Excel
		}
		
		//Excel
				try {
					FileOutputStream fileOut =  new FileOutputStream(filename);
			        workbook.write(fileOut);
			        fileOut.close();
			        System.out.println("Arquivo gerado com sucesso!!");
				}
				catch(Exception e) {
					System.out.println(e);
				}
				//Excel
	}

	public void selectsChamadas1000x() {
		// ** Métrica de Selects Chamadas mais de 100 vezes **
		ObterMetricas obterMetricas1 = new ObterMetricas(loginModel);
		List<SelectsChamadas1000xModel> selectsChamadas1000x = obterMetricas1.SelectsChamadas1000x();
		
		//Excel
		int contColunmExcel = 0;
		String dataAtual = getDateTime();
		String filename = caminho.getNome() + "SelectsChamadasMuitasVezes"+ dataAtual + ".xls";
            @SuppressWarnings("resource")
			HSSFWorkbook workbook=new HSSFWorkbook();
            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
       //Excel 
		
		for (SelectsChamadas1000xModel selectsChamadas1000xModel : selectsChamadas1000x) {
			String calls = selectsChamadas1000xModel.getCalls();
			String query = selectsChamadas1000xModel.getQuery();
			String time_exec = selectsChamadas1000xModel.getTotal_exec_time();
			String date = selectsChamadas1000xModel.getDate();
			System.out.println(calls + " | " + query + " | " + time_exec + " | " + date);
			
			//Excel
            // criando as linhas
            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Calls");
            rowhead.createCell(1).setCellValue("Query");
            rowhead.createCell(2).setCellValue("Time Exec");
            rowhead.createCell(3).setCellValue("Date");
            
		//adiciona os dados no excel
			contColunmExcel +=1;
	            // local do arquivo
	           
	            // definindo seus valores
	            // por exemplo protocolo.getProtocolo();
	            HSSFRow row=   sheet.createRow((short)contColunmExcel);
	            row.createCell(0).setCellValue(calls);
	            row.createCell(1).setCellValue(query);
	            row.createCell(2).setCellValue(time_exec);
	            row.createCell(3).setCellValue(date);
	    //Excel
		}
		
		//Excel
		try {
			FileOutputStream fileOut =  new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        System.out.println("Arquivo gerado com sucesso!!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//Excel
	}

	public void selectsMaisDemoradasMedia() {
		// ** Métrica de Média das Selects Mais Demoradas **
		ObterMetricas obterMetricas2 = new ObterMetricas(loginModel);
		List<SelectsMaisDemoradasMediaModel> selectsMaisDemoradasMedia = obterMetricas2.SelectsMaisDemoradasMedia();
		
		//Excel
				int contColunmExcel = 0;
				String dataAtual = getDateTime();
				String filename = caminho.getNome() + "SelectsDemoradasMedia"+ dataAtual + ".xls";
		            @SuppressWarnings("resource")
					HSSFWorkbook workbook=new HSSFWorkbook();
		            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		       //Excel
		
		for (SelectsMaisDemoradasMediaModel selectsMaisDemoradasMediaModel : selectsMaisDemoradasMedia) {
			String date = selectsMaisDemoradasMediaModel.getData();
			String query = selectsMaisDemoradasMediaModel.getQuery();
			String time_avar = selectsMaisDemoradasMediaModel.getTempoMedio();
			System.out.println(query + " | " + time_avar + " | " + date);
			
			
			//Excel
            // criando as linhas
            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Query");
            rowhead.createCell(1).setCellValue("Tempo");
            rowhead.createCell(2).setCellValue("Data");
            
		//adiciona os dados no excel
			contColunmExcel +=1;
	            // local do arquivo
	           
	            // definindo seus valores
	            // por exemplo protocolo.getProtocolo();
	            HSSFRow row=   sheet.createRow((short)contColunmExcel);
	            row.createCell(0).setCellValue(query);
	            row.createCell(1).setCellValue(time_avar);
	            row.createCell(2).setCellValue(date);
	    //Excel
		}
		
		//Excel
				try {
					FileOutputStream fileOut =  new FileOutputStream(filename);
			        workbook.write(fileOut);
			        fileOut.close();
			        System.out.println("Arquivo gerado com sucesso!!");
				}
				catch(Exception e) {
					System.out.println(e);
				}
				//Excel
	}

	public void tamanhobancos() {
		// ** Métrica do Tamanho dos Bancos **
		ObterMetricas obterMetricas3 = new ObterMetricas(loginModel);
		List<TamanhoBancos> tamanhobancos1 = obterMetricas3.TamanhoBanco();
		
		
		//Excel
		int contColunmExcel = 0;
		String dataAtual = getDateTime();
		String filename = caminho.getNome() + "TamanhoTabelas"+ dataAtual + ".xls";
            @SuppressWarnings("resource")
			HSSFWorkbook workbook=new HSSFWorkbook();
            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
       //Excel 
       
		
		for (TamanhoBancos tamanhobancos : tamanhobancos1) {
			String nome = tamanhobancos.getNome();
			String size = tamanhobancos.getTamanho();
			String date = tamanhobancos.getData();
			System.out.println(nome + " | " + size + " | " + date);
			
			
			//Excel
	            // criando as linhas
	            HSSFRow rowhead=   sheet.createRow((short)0);
	            rowhead.createCell(0).setCellValue("Nome");
	            rowhead.createCell(1).setCellValue("Size");
	            rowhead.createCell(2).setCellValue("Date");
	            
			//adiciona os dados no excel
				contColunmExcel +=1;
		            // local do arquivo
		           
		            // definindo seus valores
		            // por exemplo protocolo.getProtocolo();
		            HSSFRow row=   sheet.createRow((short)contColunmExcel);
		            row.createCell(0).setCellValue(nome);
		            row.createCell(1).setCellValue(size);
		            row.createCell(2).setCellValue(date);
		    //Excel
	}
		
		
		//Excel
		try {
			FileOutputStream fileOut =  new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        System.out.println("Arquivo gerado com sucesso!!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//Excel
	}

	public void tamanhoTabelas() {
		// ** Métricas de Tamanho das Tabelas **
		ObterMetricas obterMetricas4 = new ObterMetricas(loginModel);
		List<TamanhoTabelasModel> tamanhoTabelas = obterMetricas4.TamanhoTabelas();
		
		//Excel
		int contColunmExcel = 0;
		String dataAtual = getDateTime();
		String filename = caminho.getNome() + "TamanhoTabelas"+ dataAtual + ".xls";
            @SuppressWarnings("resource")
			HSSFWorkbook workbook=new HSSFWorkbook();
            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
       //Excel
		
		for (TamanhoTabelasModel tamanhoTabelasModel : tamanhoTabelas) {
			String nome = tamanhoTabelasModel.getNome();
			String size = tamanhoTabelasModel.getTamanhoTotal();
			String date = tamanhoTabelasModel.getData();
			System.out.println(nome + " | " + size + " | " + date);
			
			//Excel
            // criando as linhas
            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nome");
            rowhead.createCell(1).setCellValue("Size");
            rowhead.createCell(2).setCellValue("Date");
            
		//adiciona os dados no excel
			contColunmExcel +=1;
	            // local do arquivo
	           
	            // definindo seus valores
	            // por exemplo protocolo.getProtocolo();
	            HSSFRow row=   sheet.createRow((short)contColunmExcel);
	            row.createCell(0).setCellValue(nome);
	            row.createCell(1).setCellValue(size);
	            row.createCell(2).setCellValue(date);
	    //Excel
		}
		
		//Excel
				try {
					FileOutputStream fileOut =  new FileOutputStream(filename);
			        workbook.write(fileOut);
			        fileOut.close();
			        System.out.println("Arquivo gerado com sucesso!!");
				}
				catch(Exception e) {
					System.out.println(e);
				}
				//Excel
	}
	
	public void conflicts() {
		// ** Métricas de Tamanho das Tabelas **
		ObterMetricas obterMetricas5 = new ObterMetricas(loginModel);
		List<SelectConflictsModel> conflict = obterMetricas5.SelectConflicts();
		
		//Excel
				int contColunmExcel = 0;
				String dataAtual = getDateTime();
				String filename = caminho.getNome() + "Conflitos"+ dataAtual + ".xls";
		            @SuppressWarnings("resource")
					HSSFWorkbook workbook=new HSSFWorkbook();
		            HSSFSheet sheet =  workbook.createSheet("FirstSheet");  
		       //Excel
		
		for (SelectConflictsModel selectConflictsModel : conflict) {
			String nome = selectConflictsModel.getName();
			String confl = selectConflictsModel.getConfl();
			String confl_dead = selectConflictsModel.getConfl_dead();
			String tempo = selectConflictsModel.getTempo();
			System.out.println(nome + " | " + confl + " | " + confl_dead + " | " + tempo);
			
			
			//Excel
            // criando as linhas
            HSSFRow rowhead=   sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nome");
            rowhead.createCell(1).setCellValue("Conflito");
            rowhead.createCell(2).setCellValue("Confl_dead");
            rowhead.createCell(3).setCellValue("Tempo");
            
		//adiciona os dados no excel
			contColunmExcel +=1;
	            // local do arquivo
	           
	            // definindo seus valores
	            // por exemplo protocolo.getProtocolo();
	            HSSFRow row=   sheet.createRow((short)contColunmExcel);
	            row.createCell(0).setCellValue(nome);
	            row.createCell(1).setCellValue(confl);
	            row.createCell(2).setCellValue(confl_dead);
	            row.createCell(3).setCellValue(tempo);
	    //Excel
		}
		
		//Excel
		try {
			FileOutputStream fileOut =  new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        System.out.println("Arquivo gerado com sucesso!!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//Excel
	}
}
