package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datasource.BibliotecaDatasource;
import entity.Funcionario;

public class FuncionarioDAO {

	private BibliotecaDatasource connection;

	public void cadastraFuncionario(Funcionario f) throws SQLException {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "Insert into tb_funcionario(nome, rg, cpf, salario, admin) values(?, ?, ?, ?, ?)";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getRg());
			stmt.setDouble(3, f.getCpf());
			stmt.setDouble(4, f.getSalario());
			stmt.setBoolean(5, f.isAdmin());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public Funcionario consultarFuncionarioNome(String nome) throws SQLException {
		PreparedStatement stmt = null;
		Funcionario retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, admin FROM tb_funcionario WHERE nome like ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, "%" + nome + "%");

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				retorno = new Funcionario();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setAdmin(result.getBoolean("admin"));
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

	public Funcionario consultarFuncionarioId(int id) throws SQLException {
		PreparedStatement stmt = null;
		Funcionario retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, admin FROM tb_funcionario WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				retorno = new Funcionario();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setAdmin(result.getBoolean("admin"));
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

	public void alterarFuncionario(Funcionario f) throws SQLException {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_funcionario SET nome = ?, rg = ?, cpf = ?, admin = ?" + " WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getRg());
			stmt.setDouble(3, f.getCpf());
			stmt.setBoolean(4, f.isAdmin());
			stmt.setInt(5, f.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public void excluirFuncionario(int id) throws SQLException {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_funcionario WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public ArrayList<Funcionario> consultarTodos() throws SQLException {

		PreparedStatement stmt = null;
		ArrayList<Funcionario> retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, admin FROM tb_funcionario";
			stmt = connection.getPreparedStatement(sql);

			ResultSet result = stmt.executeQuery();
			retorno = new ArrayList<>();

			while (result.next()) {
				Funcionario retornoA = new Funcionario();
				retornoA.setId(result.getInt("id"));
				retornoA.setNome(result.getString("nome"));
				retornoA.setRg(result.getString("rg"));
				retornoA.setCpf(result.getInt("cpf"));
				retornoA.setAdmin(result.getBoolean("admin"));
				retorno.add(retornoA);
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
}
