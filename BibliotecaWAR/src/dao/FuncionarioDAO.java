package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datasource.BibliotecaDatasource;
import entity.Funcionario;

public class FuncionarioDAO {

	private BibliotecaDatasource connection;
	
	public Funcionario consultarFuncionarioId(int id) {
		PreparedStatement stmt = null;
		Funcionario retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, admin FROM tb_funcionario WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Funcionario();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setAdmin(result.getBoolean("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error(e);
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return retorno;
	}
}
