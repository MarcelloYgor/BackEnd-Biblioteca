package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Autor;

public class AutorDAO {

	private BibliotecaDatasource connection;

	public void cadastrarAutor(Autor autor) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_autor VALUES(?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.setString(2, autor.getNacionalidade());

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

	public Autor consultarAutorNome(String nome) {
		PreparedStatement stmt = null;
		Autor retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_autor WHERE nome = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, nome);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Autor();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setNacionalidade(result.getString("nacionalidade"));
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
	
	public Autor consultarAutorId(int id) {
		PreparedStatement stmt = null;
		Autor retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_autor WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Autor();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setNacionalidade(result.getString("nacionalidade"));
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

	public List<Autor> pegarAutores() {
		PreparedStatement stmt = null;
		List<Autor> autores = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, nacionalidade "
					+ "from tb_autor;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			autores = new ArrayList<>();
			while (result.next()) {
				Autor autor = new Autor();
				autor.setId(result.getInt("id"));
				autor.setNome(result.getString("nome"));
				autor.setNacionalidade(result.getString("nacionalidade"));
				autores.add(autor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return autores;
	}

	public void excluirAutor(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_autor WHERE id = ?;";
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

	public void alterarAutor(Autor autor) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_autor SET nome = ?, nacionalidade = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.setString(2, autor.getNacionalidade());
			stmt.setInt(3, autor.getId());
			
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
