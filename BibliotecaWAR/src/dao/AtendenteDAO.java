package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Atendente;

public class AtendenteDAO {

	private BibliotecaDatasource connection;

	public void cadastrarAtendente(Atendente atendente) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_funcionario VALUES(?, ?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, atendente.getNome());
			stmt.setString(2, atendente.getRg());
			stmt.setLong(3, atendente.getCpf());
			stmt.setDouble(4, atendente.getSalario());
			stmt.setBoolean(5, false);

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

	public Atendente consultarAtendenteCpf(long cpf) {
		PreparedStatement stmt = null;
		Atendente retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_funcionario WHERE cpf = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setLong(1, cpf);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Atendente();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setSalario(result.getDouble("salario"));
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
	
	public Atendente consultarAtendenteId(int id) {
		PreparedStatement stmt = null;
		Atendente retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_funcionario WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Atendente();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setSalario(result.getDouble("salario"));
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

	public List<Atendente> pegarAtendentes() {
		PreparedStatement stmt = null;
		List<Atendente> atendentes = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, rg, cpf, salario, admin "
					+ "from tb_funcionario;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			atendentes = new ArrayList<>();
			while (result.next()) {
				Atendente atendente = new Atendente();
				atendente.setId(result.getInt("id"));
				atendente.setNome(result.getString("nome"));
				atendente.setRg(result.getString("rg"));
				atendente.setCpf(result.getLong("cpf"));
				atendente.setSalario(result.getDouble("salario"));
				atendente.setAdmin(result.getBoolean("admin"));
				atendentes.add(atendente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return atendentes;
	}

	public void excluirAtendente(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_funcionario WHERE id = ?;";
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

	public void alterarAtendente(Atendente atendente) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_funcionario SET nome = ?, rg = ?, cpf = ?, salario = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, atendente.getNome());
			stmt.setString(2, atendente.getRg());
			stmt.setLong(3, atendente.getCpf());
			stmt.setDouble(4, atendente.getSalario());
			stmt.setInt(5, atendente.getId());
			
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
