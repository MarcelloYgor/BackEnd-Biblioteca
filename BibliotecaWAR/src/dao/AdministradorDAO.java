package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Administrador;

public class AdministradorDAO {

	private BibliotecaDatasource connection;

	public void cadastrarAdministrador(Administrador adm) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_funcionario VALUES(?, ?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, adm.getNome());
			stmt.setString(2, adm.getRg());
			stmt.setLong(3, adm.getCpf());
			stmt.setDouble(4, adm.getSalario());
			stmt.setBoolean(5, true);

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

	public Administrador consultarAdministradorCpf(long cpf) {
		PreparedStatement stmt = null;
		Administrador retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, salario, admin FROM tb_funcionario WHERE cpf = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setLong(1, cpf);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Administrador();
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
	
	public Administrador consultarAdministradorId(int id) {
		PreparedStatement stmt = null;
		Administrador retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, salario, admin FROM tb_funcionario WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Administrador();
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

	public List<Administrador> pegarAdms() {
		PreparedStatement stmt = null;
		List<Administrador> adms = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, salario "
					+ "from tb_funcionario;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			adms = new ArrayList<>();
			while (result.next()) {
				Administrador administrador = new Administrador();
				administrador.setId(result.getInt("id"));
				administrador.setNome(result.getString("nome"));
				administrador.setRg(result.getString("rg"));
				administrador.setCpf(result.getLong("cpf"));
				administrador.setSalario(result.getDouble("salario"));
				administrador.setAdmin(result.getBoolean("admin"));
				adms.add(administrador);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return adms;
	}

	public void excluirAdministrador(int id) {
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

	public void alterarAdministrador(Administrador administrador) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_funcionario SET nome = ?, rg = ?, cpf = ?, salario = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, administrador.getNome());
			stmt.setString(2, administrador.getRg());
			stmt.setLong(3, administrador.getCpf());
			stmt.setDouble(4, administrador.getSalario());
			stmt.setInt(5, administrador.getId());
			
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