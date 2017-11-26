package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Login;

public class LoginDAO {

	private BibliotecaDatasource connection;

	public void cadastrarLogin(Login login) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_login VALUES(?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, login.getEmail());
			stmt.setInt(2, login.getFuncionario().getId());
			stmt.setString(3, login.getPassword());
			stmt.setBoolean(4, login.isAdmin());			

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Couldnt save object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public Login consultarLoginEmail(String email) throws SQLException {
		PreparedStatement stmt = null;
		Login retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select PASSWORD,ID_FUNCIONARIO,ID,EMAIL,ADMIN from tb_login where email = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, email);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				retorno = new Login();
				retorno.setEmail(result.getString("email"));
				retorno.setPassword(result.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}
	
	public Login consultarLoginId(int id) {
		PreparedStatement stmt = null;
		Login retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_login WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Login();
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				retorno.setId(result.getInt("id"));
				retorno.setFuncionario(funcionarioDAO.consultarFuncionarioId(result.getInt("id_funcionario")));
				retorno.setEmail(result.getString("email"));
				retorno.setPassword(result.getString("password"));
				retorno.setAdmin(result.getBoolean("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}

	public List<Login> pegarLogins() {
		PreparedStatement stmt = null;
		List<Login> logins = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, email, password, admin "
					+ "from tb_login;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			logins = new ArrayList<>();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			while (result.next()) {
				Login login = new Login();
				login.setId(result.getInt("id"));
				login.setFuncionario(funcionarioDAO.consultarFuncionarioId(result.getInt("id_funcionario")));
				login.setEmail(result.getString("email"));
				login.setPassword(result.getString("password"));
				login.setAdmin(result.getBoolean("admin"));
				logins.add(login);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return logins;
	}

	public void excluirLogin(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_login WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public void alterarLogin(Login login) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_login SET email = ?, password = ?, admin = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, login.getEmail());
			stmt.setString(2, login.getPassword());
			stmt.setBoolean(3, login.isAdmin());
			stmt.setInt(4, login.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}
}