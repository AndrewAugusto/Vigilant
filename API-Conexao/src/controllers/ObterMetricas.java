package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.LoginModel;
import models.SelectArchiverModel;
import models.SelectDatabaseConflictModel;
import models.SelectDbcommitModel;
import models.SelectInfosDbModel;
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
		
		String sql = "SELECT * FROM estatisticas";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoBancos> lista = new ArrayList<TamanhoBancos>();
			
			while(result.next()) {
				TamanhoBancos tamBan = new TamanhoBancos();
				
				tamBan.setNome(result.getString("db_name"));
				tamBan.setTamanho(result.getString("db_size"));
				tamBan.setData(result.getString("query_date_time"));
				
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
		
		String sql = "SELECT * FROM estatisticas_table_size";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoTabelasModel> lista = new ArrayList<TamanhoTabelasModel>();
			
			while(result.next()) {
				TamanhoTabelasModel tamTab = new TamanhoTabelasModel();
				
				tamTab.setNome(result.getString("tab_name"));
				tamTab.setTamanhoTotal(result.getString("tab_size"));
				tamTab.setData(result.getString("query_date_time"));
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
		
		String sql = "SELECT * FROM estatisticas_call_query";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsChamadas1000xModel> lista = new ArrayList<SelectsChamadas1000xModel>();
			Double num;
			
			while(result.next()) {
				SelectsChamadas1000xModel selects = new SelectsChamadas1000xModel();
				
				selects.setCalls(result.getString("calls"));
				selects.setQuery(result.getString("query_name"));
				selects.setDate(result.getString("query_date_time"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
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
		
		String sql = "\r\n"
				+ "SELECT * FROM estatisticas_time";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectMaisDemoradasModel> lista = new ArrayList<SelectMaisDemoradasModel>();
			Double num;
			
			while(result.next()) {
				SelectMaisDemoradasModel selects = new SelectMaisDemoradasModel();
				
				selects.setQuery(result.getString("calls"));
				selects.setData(result.getString("query_date_time"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
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
		
		String sql = "SELECT * FROM estatisticas_time_average";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsMaisDemoradasMediaModel> lista = new ArrayList<SelectsMaisDemoradasMediaModel>();
			Double num;
			
			while(result.next()) {
				SelectsMaisDemoradasMediaModel selects = new SelectsMaisDemoradasMediaModel();
				
				selects.setQuery(result.getString("calls"));
				selects.setData(result.getString("query_date_time"));	
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
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
	
	public ArrayList<SelectDatabaseConflictModel> SelectDatabaseConflict() {
		iniciarConexao();
		
		String sql = "\r\n"
				+ "SELECT * FROM stat_database_conflict;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectDatabaseConflictModel> lista = new ArrayList<SelectDatabaseConflictModel>();
			
			while(result.next()) {
				SelectDatabaseConflictModel selects = new SelectDatabaseConflictModel();
				
				selects.setDatname(result.getString("datname"));
				selects.setConflictlock(result.getString("conflict_lock"));
				selects.setConflictdead(result.getString("conflict_deadlock"));
				selects.setData(result.getString("query_date_time"));
				new DecimalFormat("####.00");
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<SelectArchiverModel> SelectArchiver() {
		iniciarConexao();
		
		String sql = "\r\n"
				+ "SELECT * FROM metrics_archiver;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectArchiverModel> lista = new ArrayList<SelectArchiverModel>();
			
			while(result.next()) {
				SelectArchiverModel selects = new SelectArchiverModel();
				
				selects.setLastfailed(result.getString("last_failed"));
				selects.setCountarchived(result.getString("count_archived"));
				selects.setCountfailed(result.getString("count_failed"));
				selects.setData(result.getString("query_date_time"));
				new DecimalFormat("####.00");
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<SelectInfosDbModel> SelectInfosDb() {
		iniciarConexao();
		
		String sql = "\r\n"
				+ "SELECT * FROM infos_sys;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectInfosDbModel> lista = new ArrayList<SelectInfosDbModel>();
			
			while(result.next()) {
				SelectInfosDbModel selects = new SelectInfosDbModel();
				
				selects.setDbid(result.getString("debaseid"));
				selects.setDbname(result.getString("debasename"));
				selects.setProcess(result.getString("process"));
				selects.setUsername(result.getString("username"));
				selects.setConect(result.getString("appli_conect"));
				selects.setIp(result.getString("client_ip"));
				selects.setHost(result.getString("client_host"));
				selects.setPort(result.getString("client_port"));
				selects.setStartquery(result.getString("start_query"));
				selects.setTypevent(result.getString("type_event"));
				selects.setWaitevent(result.getString("wait_event"));
				selects.setStats(result.getString("stats"));
				selects.setQuery(result.getString("querys"));
				selects.setOutype(result.getString("out_type"));
				
				selects.setData(result.getString("query_date_time"));
				new DecimalFormat("####.00");
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		finally {
			desconecta();
		}
		
	}
	
	public ArrayList<SelectDbcommitModel> SelectDbcommit() {
		iniciarConexao();
		
		String sql = "\r\n"
				+ "SELECT * FROM stat_database;";
		
		try {
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectDbcommitModel> lista = new ArrayList<SelectDbcommitModel>();
			
			while(result.next()) {
				SelectDbcommitModel selects = new SelectDbcommitModel();
				
				
				selects.setDbname(result.getString("datname"));
				selects.setCommit(result.getString("xact_commit"));
				selects.setRollback(result.getString("xact_rollback"));
				selects.setBlkread(result.getString("blks_read"));
				selects.setBlkhit(result.getString("blks_hit"));
				selects.setConflicts(result.getString("conflicts"));
				selects.setDeadlocks(result.getString("deadlocks"));
				selects.setData(result.getString("query_date_time"));
				new DecimalFormat("####.00");
				lista.add(selects);
			}
			return lista;
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		finally {
			desconecta();
		}
		
	}
	
}
