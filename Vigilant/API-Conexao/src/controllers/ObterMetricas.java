package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.LoginModel;
import models.SelectMaisDemoradasModel;
import models.SelectsChamadas1000xModel;
import models.SelectsMaisDemoradasMediaModel;
import models.TamanhoBancos;
import models.TamanhoTabelasModel;

public class ObterMetricas {
	private String driver = "org.postgresql.Driver";
	private String caminho;
	private String caminholite;
	private String porta;
	private String banco;
	private String usuario;
	private String senha;
	private Connection con;
	private Connection in;

	public ObterMetricas(LoginModel login) {
		usuario = login.getUsuario();
		senha = login.getSenha();
		banco = login.getBanco();
		porta = login.getPorta();

		caminho = "jdbc:postgresql://localhost:" + porta + "/" + banco;
		
		caminholite = "jdbc:sqlite:banco_de_dados/banco_sqlite.db";
	}

	public void iniciarConexao() throws SQLException {
		
		//System.setProperty("jdbc.drivers","org.postgresql.Driver:org.sqlite.JDBC" );		//Conexao do PostgreSQL
		
		try {
			Class.forName("org.postgresql.Driver");
			//Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		try {
		con = DriverManager.getConnection(caminho, usuario, senha);
		}catch (SQLException ex) {
			System.out.println("Falha de Conexão com PostgreSQL");
			ex.printStackTrace();
			throw ex;
		}
		
		//Conexao do SQLite
		try {
		in = DriverManager.getConnection(caminholite);
		}catch (SQLException ex) {
			System.out.println("Falha de Conexão com SQLite");
			ex.printStackTrace();
			throw ex;
		}
	}

	public void desconecta() {
		try {
			con.close();
			in.close();
		} catch (SQLException ex) {
		}
	}

	public ArrayList<TamanhoBancos> TamanhoBanco() {
		String sql = "SELECT * FROM estatisticas;";

		try {
			iniciarConexao();
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoBancos> lista = new ArrayList<TamanhoBancos>();

			while (result.next()) {
				TamanhoBancos tamBan = new TamanhoBancos();

				tamBan.setNome(result.getString("db_name"));
				tamBan.setTamanho(result.getString("db_size"));
				tamBan.setData(result.getString("query_date_time"));

				lista.add(tamBan);
			}

			return lista;
		} catch (SQLException e) {
			throw new SQLRunTimeException(e.getMessage(), e);
		} finally {
			desconecta();
		}
	}

	public ArrayList<TamanhoTabelasModel> TamanhoTabelas() {

		String sql = "SELECT * FROM estatisticas_table_size;";

		try {
			iniciarConexao();
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<TamanhoTabelasModel> lista = new ArrayList<TamanhoTabelasModel>();

			String query = "INSERT INTO Users ("
				    + " user_id,"
				    + " username,"
				    + " firstname,"
				    + " lastname,"
				    + " companyname,"
				    + " email_addr,"
				    + " want_privacy ) VALUES ("
				    + "null, ?, ?, ?, ?, ?, ?)";
			
			//PreparedStatement st = in.prepareStatement(query);
			
			while (result.next()) {
				TamanhoTabelasModel tamTab = new TamanhoTabelasModel();

				tamTab.setNome(result.getString("tab_name"));
				tamTab.setTamanhoTotal(result.getString("tab_size"));
				tamTab.setData(result.getString("query_date_time"));
				lista.add(tamTab);
				
				/*st.setString(1, tamTab.getNome());
				st.setString(2, tamTab.getTamanhoTotal());
				st.setString(3, tamTab.getData());
				st.executeUpdate();*/
				
			}
			return lista;
		} catch (SQLException e) {
			throw new SQLRunTimeException(e.getMessage(), e);
		} finally {
			desconecta();
		}
	}

	public ArrayList<SelectsChamadas1000xModel> SelectsChamadas1000x() {

		String sql = "SELECT * FROM estatisticas_call_query";

		try {
			iniciarConexao();
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsChamadas1000xModel> lista = new ArrayList<SelectsChamadas1000xModel>();
			Double num;

			while (result.next()) {
				SelectsChamadas1000xModel selects = new SelectsChamadas1000xModel();
				selects.setCalls(result.getString("calls"));
				selects.setQuery(result.getString("query_name"));
				selects.setDate(result.getString("query_date_time"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
				selects.setTotal_exec_time(df.format(num).toString());
				lista.add(selects);
			}
			return lista;
		} catch (SQLException e) {
			throw new SQLRunTimeException(e.getMessage(), e);
		} finally {
			desconecta();
		}
	}

	public List<SelectMaisDemoradasModel> SelectMaisDemoradas() {

		String sql = "\r\n" + "SELECT * FROM estatisticas_time";

		try {
			iniciarConexao();
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			List<SelectMaisDemoradasModel> lista = new LinkedList<SelectMaisDemoradasModel>();
			Double num;

			while (result.next()) {
				SelectMaisDemoradasModel selects = new SelectMaisDemoradasModel();

				selects.setQuery(result.getString("calls"));
				selects.setData(result.getString("query_date_time"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
				selects.setTempo(df.format(num).toString());
				lista.add(selects);
			}
			return lista;
		} catch (SQLException e) {
			throw new SQLRunTimeException(e.getMessage(), e);
		} finally {
			desconecta();
		}
	}

	public ArrayList<SelectsMaisDemoradasMediaModel> SelectsMaisDemoradasMedia() {

		String sql = "SELECT * FROM estatisticas_time_average";

		try {
			iniciarConexao();
			PreparedStatement pesquisa = con.prepareStatement(sql);
			ResultSet result = pesquisa.executeQuery();
			ArrayList<SelectsMaisDemoradasMediaModel> lista = new ArrayList<SelectsMaisDemoradasMediaModel>();
			Double num;

			while (result.next()) {

				SelectsMaisDemoradasMediaModel selects = new SelectsMaisDemoradasMediaModel();
				selects.setQuery(result.getString("calls"));
				selects.setData(result.getString("query_date_time"));
				DecimalFormat df = new DecimalFormat("####.00");
				num = Double.parseDouble(result.getString("time_exec"));
				selects.setTempoMedio(df.format(num).toString());
				lista.add(selects);
			}
			return lista;
		} catch (SQLException e) {
			throw new SQLRunTimeException(e.getMessage(), e);
		} finally {
			desconecta();
		}
	}
}
