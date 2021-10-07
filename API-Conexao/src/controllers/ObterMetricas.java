package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.LoginModel;
import models.SelectMaisDemoradasModel;
import models.SelectsChamadas1000xModel;
import models.SelectsMaisDemoradasMediaModel;
import models.TamanhoBancos;
import models.TamanhoTabelasModel;

public class ObterMetricas {
	
	
	private String driver = "org.postgresql.Driver";
	private String caminho;
	private String porta;
	private String banco;
	private String usuario;
	private String senha;
	public Connection  con;
	
	public ObterMetricas(LoginModel login) {
		usuario = login.getUsuario();
		senha = login.getSenha();
		banco = login.getBanco();
		porta = login.getPorta();
		
		caminho = "jdbc:postgresql://localhost:" + porta + "/" + banco;
	}
	
	public void iniciarConexao() {
		// Necessário alterar a url, usuario e senha para o banco que será conectado
		try {
			System.setProperty("jdbc.Drivers",driver );
			con = DriverManager.getConnection(caminho,usuario,senha);
				//JOptionPane.showMessageDialog(null, "Conexão efetuada com sucesso!");
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro de conexão: \n"+ex.getMessage());
		}

	}
	
	public void desconecta() {
		try {
			con.close();
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar desconectar: \n"+ex.getMessage());
		}
	}

	public ArrayList<TamanhoBancos> TamanhoBanco() {
		iniciarConexao();
		
		String sql = "SELECT pg_database.datname, pg_size_pretty(pg_database_size(pg_database.datname)) AS size FROM pg_database;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoBancos> lista = new ArrayList<TamanhoBancos>();
			
			while(result.next()) {
				TamanhoBancos tamBan = new TamanhoBancos();
				
				tamBan.setNome(result.getString("datname"));
				tamBan.setTamanho(result.getString("size"));
				
				lista.add(tamBan);
			}
			
			return lista;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<TamanhoTabelasModel> TamanhoTabelas() {
		iniciarConexao();
		
		String sql = "SELECT esquema, tabela,\r\n"
				+ "       pg_size_pretty(pg_relation_size(esq_tab)) AS tamanho,\r\n"
				+ "       pg_size_pretty(pg_total_relation_size(esq_tab)) AS tamanho_total\r\n"
				+ "  FROM (SELECT tablename AS tabela,\r\n"
				+ "               schemaname AS esquema,\r\n"
				+ "               schemaname||'.'||tablename AS esq_tab\r\n"
				+ "          FROM pg_catalog.pg_tables\r\n"
				+ "         WHERE schemaname NOT\r\n"
				+ "            IN ('pg_catalog', 'information_schema', 'pg_toast') ) AS x\r\n"
				+ " ORDER BY pg_total_relation_size(esq_tab) DESC;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoTabelasModel> lista = new ArrayList<TamanhoTabelasModel>();
			
			while(result.next()) {
				TamanhoTabelasModel tamTab = new TamanhoTabelasModel();
				
				tamTab.setNome(result.getString("tabela"));
				tamTab.setTamanhoTotal(result.getString("tamanho_total"));
				lista.add(tamTab);
			}
			return lista;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<SelectsChamadas1000xModel> SelectsChamadas1000x() {
		iniciarConexao();
		
		String sql = "SELECT calls, query, total_exec_time\r\n"
				+ "FROM pg_stat_statements\r\n"
				+ "where calls > 1000;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsChamadas1000xModel> lista = new ArrayList<SelectsChamadas1000xModel>();
			Double num;
			
			while(result.next()) {
				SelectsChamadas1000xModel selects = new SelectsChamadas1000xModel();
				
				selects.setCalls(result.getString("calls"));
				selects.setQuery(result.getString("query"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("total_exec_time"));
				selects.setTotal_exec_time(df.format(num).toString() );
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			desconecta();
		}
		
		
	}
	
	public ArrayList<SelectMaisDemoradasModel> SelectMaisDemoradas() {
		iniciarConexao();
		
		String sql = "SELECT total_exec_time, query\r\n"
				+ "FROM pg_stat_statements\r\n"
				+ "ORDER BY total_exec_time\r\n"
				+ "DESC LIMIT 10;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectMaisDemoradasModel> lista = new ArrayList<SelectMaisDemoradasModel>();
			Double num;
			
			while(result.next()) {
				SelectMaisDemoradasModel selects = new SelectMaisDemoradasModel();
				
				selects.setQuery(result.getString("query"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("total_exec_time"));
				selects.setTempo(df.format(num).toString() );
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<SelectsMaisDemoradasMediaModel> SelectsMaisDemoradasMedia() {
		iniciarConexao();
		
		String sql = "SELECT mean_exec_time, query\r\n"
				+ "FROM pg_stat_statements\r\n"
				+ "ORDER BY mean_exec_time\r\n"
				+ "DESC LIMIT 10;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsMaisDemoradasMediaModel> lista = new ArrayList<SelectsMaisDemoradasMediaModel>();
			Double num;
			
			while(result.next()) {
				SelectsMaisDemoradasMediaModel selects = new SelectsMaisDemoradasMediaModel();
				
				selects.setQuery(result.getString("query"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("mean_exec_time"));
				selects.setTempoMedio(df.format(num).toString() );
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			desconecta();
		}
		
	}
}
