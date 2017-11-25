package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BibliotecaDatasource {

private Connection connection;
	
	public BibliotecaDatasource() {
		if (connection == null) {
			connection = abrirConexao();
		}
	}

	public Connection abrirConexao() {
		Connection conn = null;
		try {
			System.out.println("Conectando no banco de dados");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connection = "jdbc:sqlserver://meubancoaz.database.windows.net:1433;databaseName=teste";
			String usuario = "roote";
			String senha = "Abcd1234";
			conn = DriverManager.getConnection(connection, usuario, senha);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error(e);
		} if (conn != null) {
			System.out.println("Conectado com sucesso!");
		} else if (conn == null) {
			System.out.println("Falha ao conectar!");
		}
		return conn;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error(e);
		}
		return pstmt;
	}
	
	public Boolean closeConnection(PreparedStatement pstmt) {
		try {
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Error(e);
		}
		return true;
	}
}
