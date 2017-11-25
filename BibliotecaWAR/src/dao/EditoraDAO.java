package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Editora;

public class EditoraDAO {

	private BibliotecaDatasource connection;

	public void cadastrarEditora(Editora editora) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_editora VALUES(?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.setString(2, editora.getEndereco());
			stmt.setString(3, editora.getNacionalidade());

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

	public Editora consultarEditoraNome(String nome) {
		PreparedStatement stmt = null;
		Editora retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_editora WHERE nome = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, nome);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Editora();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setEndereco(result.getString("endereco"));
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
	
	public Editora consultarEditoraId(int id) {
		PreparedStatement stmt = null;
		Editora retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_editora WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Editora();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setEndereco(result.getString("endereco"));
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

	public List<Editora> pegarEditora() {
		PreparedStatement stmt = null;
		List<Editora> editoras = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, endereco, nacionalidade "
					+ "from tb_editora;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			editoras = new ArrayList<>();
			while (result.next()) {
				Editora editora = new Editora();
				editora.setId(result.getInt("id"));
				editora.setNome(result.getString("nome"));
				editora.setEndereco(result.getString("endereco"));
				editora.setNacionalidade(result.getString("nacionalidade"));
				editoras.add(editora);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return editoras;
	}

	public void excluirEditora(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_editora WHERE id = ?;";
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

	public void alterarEditora(Editora editora) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_editora SET nome = ?, endereco = ?, nacionalidade = ? WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.setString(2, editora.getEndereco());
			stmt.setString(3, editora.getNacionalidade());
			stmt.setInt(4, editora.getId());
			
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