package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Emprestimo;

public class EmprestimoDAO {

	private BibliotecaDatasource connection;

	public void cadastrarEmprestimo(Emprestimo emprestimo) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_emprestimo VALUES(?, ?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, emprestimo.getCliente().getId());
			stmt.setInt(2, emprestimo.getFuncionario().getId());
			stmt.setDate(3, converteData(emprestimo.getDataEmprestimo()));
			stmt.setDate(4, converteData(emprestimo.getDataDevolucao()));
			stmt.setBoolean(5, emprestimo.isActive());

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

	public Emprestimo consultarEmprestimo(int idCliente, int idFunc, java.util.Date dtEmprestimo) {
		PreparedStatement stmt = null;
		Emprestimo retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_emprestimo WHERE id_cliente = ? AND id_funcionario = ? AND data_emprestimo = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, idCliente);
			stmt.setInt(2, idFunc);
			stmt.setDate(3, converteData(dtEmprestimo));

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Emprestimo();
				ClienteDAO cliente = new ClienteDAO();
				FuncionarioDAO funcionario = new FuncionarioDAO();
				LivrosEmprestados livrosEmprestados = new LivrosEmprestados();
				retorno.setId(result.getInt("id"));
				retorno.setLivros(livrosEmprestados.pegarLivrosEmprestados(retorno.getId()));
				retorno.setCliente(cliente.consultarClienteId(result.getInt("id_cliente")));
				retorno.setFuncionario(funcionario.consultarFuncionarioId(result.getInt("id_funcionario")));
				retorno.setDataEmprestimo(result.getDate("data_emprestimo"));
				retorno.setDataDevolucao(result.getDate("data_devolucao"));
				retorno.setActive(result.getBoolean("active"));
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

	public Emprestimo consultarEmprestimoId(int id) {
		PreparedStatement stmt = null;
		Emprestimo retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT * FROM tb_emprestimo WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Emprestimo();
				ClienteDAO cliente = new ClienteDAO();
				FuncionarioDAO funcionario = new FuncionarioDAO();
				LivrosEmprestados livrosEmprestados = new LivrosEmprestados();
				retorno.setId(result.getInt("id"));
				retorno.setLivros(livrosEmprestados.pegarLivrosEmprestados(retorno.getId()));
				retorno.setCliente(cliente.consultarClienteId(result.getInt("id_cliente")));
				retorno.setFuncionario(funcionario.consultarFuncionarioId(result.getInt("id_funcionario")));
				retorno.setDataEmprestimo(result.getDate("data_emprestimo"));
				retorno.setDataDevolucao(result.getDate("data_devolucao"));
				retorno.setActive(result.getBoolean("active"));
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

	public List<Emprestimo> pegarEmprestimos() {
		PreparedStatement stmt = null;
		List<Emprestimo> emprestimos = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, id_cliente, id_funcionario, data_emprestimo, data_devolucao, active "
					+ "from tb_emprestimo;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			emprestimos = new ArrayList<>();
			LivrosEmprestados livrosEmprestados = new LivrosEmprestados();
			while (result.next()) {
				Emprestimo emprestimo = new Emprestimo();
				ClienteDAO cliente = new ClienteDAO();
				FuncionarioDAO funcionario = new FuncionarioDAO();
				emprestimo.setId(result.getInt("id"));
				emprestimo.setLivros(livrosEmprestados.pegarLivrosEmprestados(emprestimo.getId()));
				emprestimo.setCliente(cliente.consultarClienteId(result.getInt("id_cliente")));
				emprestimo.setFuncionario(funcionario.consultarFuncionarioId(result.getInt("id_funcionario")));
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

	public void excluirEmprestimo(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_emprestimo WHERE id = ?;";
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

	public void alterarEmprestimo(Emprestimo emprestimo) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_emprestimo SET id_cliente = ?, id_funcionario = ?, data_emprestimo = ?, data_devolucao = ?, active = ?"
					+ " WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, emprestimo.getCliente().getId());
			stmt.setInt(2, emprestimo.getFuncionario().getId());
			stmt.setDate(3, converteData(emprestimo.getDataEmprestimo()));
			stmt.setDate(4, converteData(emprestimo.getDataDevolucao()));
			stmt.setBoolean(5, emprestimo.isActive());
			stmt.setInt(6, emprestimo.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.closeConnection(stmt);
			}
		}
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