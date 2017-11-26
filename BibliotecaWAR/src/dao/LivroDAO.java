package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Livro;

public class LivroDAO {

	private BibliotecaDatasource connection;

	public void cadastrarLivro(Livro livro) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_Livro VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, livro.getNome());
			stmt.setInt(2, livro.getAutor().getId());
			stmt.setInt(3, livro.getEditora().getId());
			stmt.setInt(4, livro.getAno());
			stmt.setInt(5, livro.getEdicao());
			stmt.setInt(6, livro.getNumPaginas());
			stmt.setInt(7, livro.getGenero().getId());
			stmt.setString(8, livro.getIdioma());
			stmt.setInt(9, Livro.getQtdDisponivel());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Couldnt save object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
			throw new Error(e);
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public Livro consultarLivroNome(String nome) {
		PreparedStatement stmt = null;
		Livro retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, id_autor, id_editora, ano, edicao, num_paginas, id_genero, idioma, qtd_disponivel FROM tb_Livro WHERE nome = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, nome);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Livro();
				AutorDAO autor = new AutorDAO();
				EditoraDAO editora = new EditoraDAO();
				GeneroDAO genero = new GeneroDAO();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setAutor(autor.consultarAutorId(result.getInt("id_autor")));
				retorno.setEditora(editora.consultarEditoraId(result.getInt("id_editora")));
				retorno.setAno(result.getInt("ano"));
				retorno.setEdicao(result.getInt("edicao"));
				retorno.setNumPaginas(result.getInt("num_paginas"));
				retorno.setGenero(genero.consultarGeneroId(result.getInt("id_genero")));
				retorno.setIdioma(result.getString("idioma"));
				Livro.setQtdDisponivel(result.getInt("qtd_disponivel"));
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

	public Livro consultarLivroId(int id) {
		PreparedStatement stmt = null;
		Livro retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, id_autor, id_editora, ano, edicao, num_paginas, id_genero, idioma, qtd_disponivel FROM tb_Livro WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Livro();
				AutorDAO autor = new AutorDAO();
				EditoraDAO editora = new EditoraDAO();
				GeneroDAO genero = new GeneroDAO();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setAutor(autor.consultarAutorId(result.getInt("id_autor")));
				retorno.setEditora(editora.consultarEditoraId(result.getInt("id_editora")));
				retorno.setAno(result.getInt("ano"));
				retorno.setEdicao(result.getInt("edicao"));
				retorno.setNumPaginas(result.getInt("num_paginas"));
				retorno.setGenero(genero.consultarGeneroId(result.getInt("id_genero")));
				retorno.setIdioma(result.getString("idioma"));
				Livro.setQtdDisponivel(result.getInt("qtd_disponivel"));
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

	public List<Livro> pegarLivros() {
		PreparedStatement stmt = null;
		List<Livro> livros = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, id_autor, id_editora, ano, edicao, num_paginas, id_genero, idioma, qtd_disponivel "
					+ "from tb_livro;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			livros = new ArrayList<>();
			while (result.next()) {
				Livro livro = new Livro();
				AutorDAO autor = new AutorDAO();
				EditoraDAO editora = new EditoraDAO();
				GeneroDAO genero = new GeneroDAO();
				livro.setId(result.getInt("id"));
				livro.setNome(result.getString("nome"));
				livro.setAutor(autor.consultarAutorId(result.getInt("id_autor")));
				livro.setEditora(editora.consultarEditoraId(result.getInt("id_editora")));
				livro.setAno(result.getInt("ano"));
				livro.setEdicao(result.getInt("edicao"));
				livro.setNumPaginas(result.getInt("num_paginas"));
				livro.setGenero(genero.consultarGeneroId(result.getInt("id_genero")));
				livro.setIdioma(result.getString("idioma"));
				Livro.setQtdDisponivel(result.getInt("qtd_disponivel"));
				livros.add(livro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Error(e);
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return livros;
	}

	public void excluirLivro(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_livro WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error(e);
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public void alterarLivro(Livro livro) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_Livro SET nome = ?, id_autor = ?, id_editora = ?, ano = ?, edicao = ?, num_paginas = ?, id_genero = ?, idioma = ?, qtd_disponivel = ?"
					+ " WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, livro.getNome());
			stmt.setInt(2, livro.getAutor().getId());
			stmt.setInt(3, livro.getEditora().getId());
			stmt.setInt(4, livro.getAno());
			stmt.setInt(5, livro.getEdicao());
			stmt.setInt(6, livro.getNumPaginas());
			stmt.setInt(7, livro.getGenero().getId());
			stmt.setString(8, livro.getIdioma());
			stmt.setInt(9, Livro.getQtdDisponivel());
			stmt.setInt(10, livro.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error(e);
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
	}

	public ArrayList<Livro> consultarLivroTodos() {

		PreparedStatement stmt = null;
		ArrayList<Livro> retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_Livro";
			stmt = connection.getPreparedStatement(sql);

			ResultSet result = stmt.executeQuery();
			retorno = new ArrayList<>();

			if (result.next()) {
				Livro retornoA = new Livro();
				AutorDAO autor = new AutorDAO();
				EditoraDAO editora = new EditoraDAO();
				GeneroDAO genero = new GeneroDAO();
				retornoA.setId(result.getInt("id"));
				retornoA.setNome(result.getString("nome"));
				retornoA.setAutor(autor.consultarAutorId(result.getInt("id_autor")));
				retornoA.setEditora(editora.consultarEditoraId(result.getInt("id_editora")));
				retornoA.setAno(result.getInt("ano"));
				retornoA.setEdicao(result.getInt("edicao"));
				retornoA.setNumPaginas(result.getInt("num_paginas"));
				retornoA.setGenero(genero.consultarGeneroId(result.getInt("id_genero")));
				retornoA.setIdioma(result.getString("idioma"));
				Livro.setQtdDisponivel(result.getInt("qtd_disponivel"));
				retorno.add(retornoA);
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