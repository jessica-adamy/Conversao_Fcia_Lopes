package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection sqlConn = null, sqlConnAux = null, sqlConnConsulta = null;
	
	public static String SQL_SERVIDOR = "";
	public static String SQL_BANCO = "";
	public static String SQL_USUARIO= "";
	public static String SQL_SENHA = "";
	public static String SQL_SERVIDOR_CONSULTA = "";
	public static String SQL_BANCO_CONSULTA = "";
	public static String SQL_USUARIO_CONSULTA = "";
	public static String SQL_SENHA_CONSULTA = "";
	
//	Conexão SqlServer
	public static Connection getSqlConnection() {
		try {
			if (sqlConn == null || sqlConn.isClosed()) {			
				String url = "jdbc:jtds:sqlserver://" + SQL_SERVIDOR + "/" + SQL_BANCO;
				String usuario = SQL_USUARIO;
				String senha = SQL_SENHA;
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				sqlConn = DriverManager.getConnection(url, usuario, senha);
				System.out.println("conectou " + SQL_BANCO);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro de drive: " + e.getMessage());
		}
		return sqlConn;
	}
//	Conexão Auxiliar SqlServer
	public static Connection getSqlConnectionAux() {
		try {
			if (sqlConnAux == null || sqlConnAux.isClosed()) {			
				String url = "jdbc:jtds:sqlserver://" + SQL_SERVIDOR + "/" + SQL_BANCO;
				String usuario = SQL_USUARIO_CONSULTA;
				String senha = SQL_SENHA_CONSULTA;
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				sqlConnAux = DriverManager.getConnection(url, usuario, senha);
				System.out.println("conectou " + SQL_BANCO);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro de drive: " + e.getMessage());
		}
		return sqlConnAux;
	}
}