package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Livro;

public class LivrosEmprestados {

	private BibliotecaDatasource connection;

	public void cadastrarLivrosEmprestados(List<Livro> livros, int id) {
		PreparedStatement stmt = null;
		for (Livro livro : livros) {
			try {
				connection = new BibliotecaDatasource();
				String sql = "INSERT INTO tb_livros_emprestados VALUES(?, ?);";
				stmt = connection.getPreparedStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, livro.getId());

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
	}

	public List<Livro> pegarLivrosEmprestados(int id) {
		PreparedStatement stmt = null;
		List<Livro> livros = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id_livro "
					+ "from tb_livros_emprestados WHERE id_emprestimo = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();

			livros = new ArrayList<>();
			while (result.next()) {
				Livro livro = new Livro();
				LivroDAO livroDAO = new LivroDAO();
				livro = livroDAO.consultarLivroId(result.getInt("id_livro"));
				livros.add(livro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return livros;
	}
	
	public void excluirLivrosEmprestados(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_livros_emprestados WHERE id_emprestimo = ?;";
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
}
