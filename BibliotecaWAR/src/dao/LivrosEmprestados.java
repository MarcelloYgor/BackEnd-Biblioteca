package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Cliente;
import entity.Livro;
import rest.emprestimo.vo.EmprestimosVo;

public class LivrosEmprestados {

	private BibliotecaDatasource connection;

	public void cadastrarLivrosEmprestados(Livro livro, int id) throws SQLException {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_livros_emprestados(id_emprestimo, id_livro) VALUES(?, ?)";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, livro.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Couldnt save object in database!\n SqlState: " + e.getSQLState() + "\nErrorCode: "
					+ e.getErrorCode() + " " + "\nMessage: " + e.getMessage());
			throw e;
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
	}
	
	public EmprestimosVo pegarLivroEmprestado(int id) throws SQLException {
		PreparedStatement stmt = null;
		EmprestimosVo emprestimo = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select * from TB_EMPRESTIMO e inner join TB_livros_emprestados le on e.id = le.id_emprestimo \n" + 
					"    where e.active = 1 and id_emprestimo = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();

			emprestimo = new EmprestimosVo();
			while (result.next()) {
				LivroDAO livroDAO = new LivroDAO();
				Livro livro = livroDAO.consultarLivroId(result.getInt("id_livro"));
				emprestimo.setLivro(livro);
				
				ClienteDAO clienteDao = new ClienteDAO();
				Cliente cliente = clienteDao.consultarClienteId(result.getInt("id_cliente"));
				emprestimo.setCliente(cliente);
				emprestimo.setDataEmprestimo(result.getDate("data_emprestimo"));
				emprestimo.setDataDevolucao(result.getDate("data_devolucao"));
				emprestimo.setId(result.getInt("id_emprestimo"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return emprestimo;
	}
	

	public List<EmprestimosVo> pegarLivrosEmprestados() throws SQLException {
		PreparedStatement stmt = null;
		List<EmprestimosVo> emprestimos = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select * from TB_EMPRESTIMO e inner join TB_livros_emprestados le on e.id = le.id_emprestimo \n" + 
					"    where e.active = 1";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			emprestimos = new ArrayList<>();
			while (result.next()) {
				EmprestimosVo emprestimo = new EmprestimosVo();
				LivroDAO livroDAO = new LivroDAO();
				Livro livro = livroDAO.consultarLivroId(result.getInt("id_livro"));
				emprestimo.setLivro(livro);
				
				ClienteDAO clienteDao = new ClienteDAO();
				Cliente cliente = clienteDao.consultarClienteId(result.getInt("id_cliente"));
				emprestimo.setCliente(cliente);
				emprestimo.setDataEmprestimo(result.getDate("data_emprestimo"));
				emprestimo.setDataDevolucao(result.getDate("data_devolucao"));
				emprestimo.setId(result.getInt("id_emprestimo"));
				
				emprestimos.add(emprestimo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return emprestimos;
	}

	public void excluirLivrosEmprestados(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_livros_emprestados WHERE id_emprestimo = ?";
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
