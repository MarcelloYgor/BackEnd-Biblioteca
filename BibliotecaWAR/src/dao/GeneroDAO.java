package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Genero;

public class GeneroDAO {

	private BibliotecaDatasource connection;

	public void cadastrarGenero(Genero genero) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_genero VALUES(?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, genero.getNome());
			stmt.setString(2, genero.getDescricao());

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

	public Genero consultarGeneroNome(String nome) {
		PreparedStatement stmt = null;
		Genero retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_genero WHERE nome = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, nome);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Genero();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setDescricao(result.getString("descricao"));
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
	
	public Genero consultarGeneroId(int id) {
		PreparedStatement stmt = null;
		Genero retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_genero WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Genero();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setDescricao(result.getString("descricao"));
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

	public List<Genero> pegarGeneros() {
		PreparedStatement stmt = null;
		List<Genero> generos = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, descricao "
					+ "from tb_Genero;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			generos = new ArrayList<>();
			while (result.next()) {
				Genero genero = new Genero();
				genero.setId(result.getInt("id"));
				genero.setNome(result.getString("nome"));
				genero.setDescricao(result.getString("descricao"));
				generos.add(genero);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return generos;
	}

	public void excluirGenero(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_genero WHERE id = ?;";
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

	public void alterarGenero(Genero genero) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_genero SET nome = ?, descricao = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, genero.getNome());
			stmt.setString(2, genero.getDescricao());
			stmt.setInt(3, genero.getId());
			
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