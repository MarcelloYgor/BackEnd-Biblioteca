package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Emprestimo;
import entity.Livro;

public class RelatoriosDAO {

	private BibliotecaDatasource connection;

	//Chama procedure no banco que traz um numero de emprestimos entre as datas passadas
	public int pegarEmprestimosData(java.util.Date dtEmprestimo, java.util.Date dtDevolucao) {
		int count = 0;
		CallableStatement callStmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "CALL numero_emprestimos(?, ?)";
			callStmt = connection.prepareCall(sql);
			callStmt.setDate(1, converteData(dtEmprestimo));
			callStmt.setDate(2, converteData(dtDevolucao));

			ResultSet result = callStmt.executeQuery();
			result.next();
			count = result.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(callStmt);
			}
		}
		return count;
	}

	//Chama procedure no banco que traz um numero de livros emprestados entre as datas passadas
	public int pegarLivrosData(java.util.Date dtEmprestimo, java.util.Date dtDevolucao) {
		int count = 0;
		CallableStatement callStmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "CALL numero_livros(?, ?)";
			callStmt = connection.prepareCall(sql);
			callStmt.setDate(1, converteData(dtEmprestimo));
			callStmt.setDate(2, converteData(dtDevolucao));

			ResultSet result = callStmt.executeQuery();
			result.next();
			count = result.getInt(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(callStmt);
			}
		}
		return count;
	}

	//Traz o número de empréstimos para cada livro(aqui tem GAMBETA)
	public List<Livro> pegarLivrosTops() {
		List<Emprestimo> emprestimos = pegarEmprestimos();
		List<Livro> livros = null;
		for (Emprestimo emprestimo : emprestimos) {
			CallableStatement callStmt = null;
			try {
				connection = new BibliotecaDatasource();
				String sql = "CALL livros_tops(?)";
				callStmt = connection.prepareCall(sql);
				callStmt.setInt(1, emprestimo.getId());

				ResultSet result = callStmt.executeQuery();
				while (result.next()) {
					Livro livro = new Livro();
					livro.setId(result.getInt("id_livro"));
					//Pegar o ano e somar no controle para cada livro(é a quantidade de livros emprestados por emprestimo(gambiarra da porra))
					livro.setAno(result.getInt("livro_count"));
					livros.add(livro);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.closeConnection(callStmt);
				}
			}
		}
		return livros;
	}

	private List<Emprestimo> pegarEmprestimos() {
		PreparedStatement stmt = null;
		List<Emprestimo> emprestimos = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, id_cliente, data_emprestimo, data_devolucao, active " + "from tb_emprestimo";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			emprestimos = new ArrayList<>();
			LivrosEmprestados livrosEmprestados = new LivrosEmprestados();
			while (result.next()) {
				Emprestimo emprestimo = new Emprestimo();
				ClienteDAO cliente = new ClienteDAO();
				emprestimo.setId(result.getInt("id"));
				emprestimo.setLivros(livrosEmprestados.pegarLivrosEmprestados(emprestimo.getId()));
				emprestimo.setCliente(cliente.consultarClienteId(result.getInt("id_cliente")));
				emprestimo.setDataEmprestimo(result.getDate("data_emprestimo"));
				emprestimo.setDataDevolucao(result.getDate("data_devolucao"));
				emprestimo.setActive(result.getBoolean("active"));
				emprestimos.add(emprestimo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return emprestimos;
	}

	private java.sql.Date converteData(java.util.Date dataUtil) {
		java.sql.Date dataSql;
		if (dataUtil != null) {
			dataSql = new java.sql.Date(dataUtil.getTime());
		} else {
			dataSql = null;
		}
		return dataSql;
	}
}
