package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.BibliotecaDatasource;
import entity.Cliente;

public class ClienteDAO {

	private BibliotecaDatasource connection;

	public void cadastrarCliente(Cliente cliente) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "INSERT INTO tb_cliente VALUES(?, ?, ?, ?, ?, ?, ?);";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRg());
			stmt.setLong(3, cliente.getCpf());
			stmt.setString(4, cliente.getEndereco());
			stmt.setLong(5, cliente.getTelefone());
			stmt.setString(6, cliente.getCidade());
			stmt.setDate(7, converteData(cliente.getNascimento()));			

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

	public Cliente consultarClienteCpf(long cpf) {
		PreparedStatement stmt = null;
		Cliente retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, endereco, telefone, cidade, dt_nascimento FROM tb_cliente WHERE cpf = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setLong(1, cpf);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Cliente();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setEndereco(result.getString("endereco"));
				retorno.setTelefone(result.getLong("telefone"));
				retorno.setCidade(result.getString("cidade"));
				retorno.setNascimento(result.getDate("dt_nascimento"));
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
	
	public Cliente consultarClienteId(int cpf) {
		PreparedStatement stmt = null;
		Cliente retorno = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "SELECT id, nome, rg, cpf, endereco, telefone, cidade, dt_nascimento FROM tb_cliente WHERE id = ?";
			stmt = connection.getPreparedStatement(sql);
			stmt.setInt(1, cpf);

			ResultSet result = stmt.executeQuery();
			if (result.isBeforeFirst()) {
				result.next();
				retorno = new Cliente();
				retorno.setId(result.getInt("id"));
				retorno.setNome(result.getString("nome"));
				retorno.setRg(result.getString("rg"));
				retorno.setCpf(result.getLong("cpf"));
				retorno.setEndereco(result.getString("endereco"));
				retorno.setTelefone(result.getLong("telefone"));
				retorno.setCidade(result.getString("cidade"));
				retorno.setNascimento(result.getDate("dt_nascimento"));
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

	public List<Cliente> pegarClientes() {
		PreparedStatement stmt = null;
		List<Cliente> clientes = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "select id, nome, rg, cpf, endereco, telefone, cidade, dt_nascimento "
					+ "from tb_cliente;";
			stmt = connection.getPreparedStatement(sql);
			ResultSet result = stmt.executeQuery();

			clientes = new ArrayList<>();
			while (result.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id"));
				cliente.setNome(result.getString("nome"));
				cliente.setRg(result.getString("rg"));
				cliente.setCpf(result.getLong("cpf"));
				cliente.setEndereco(result.getString("endereco"));
				cliente.setTelefone(result.getLong("telefone"));
				cliente.setCidade(result.getString("cidade"));
				cliente.setNascimento(result.getDate("dt_nascimento"));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				connection.closeConnection(stmt);
			}
		}
		return clientes;
	}

	public void excluirCliente(int id) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "DELETE FROM tb_cliente WHERE id = ?;";
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

	public void alterarCliente(Cliente cliente) {
		PreparedStatement stmt = null;
		try {
			connection = new BibliotecaDatasource();
			String sql = "UPDATE tb_cliente SET nome = ?, rg = ?, cpf = ?, endereco = ?, telefone = ?, cidade = ?, dt_nascimento = ?"
					+ " WHERE id = ?;";
			stmt = connection.getPreparedStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRg());
			stmt.setLong(3, cliente.getCpf());
			stmt.setString(4, cliente.getEndereco());
			stmt.setLong(5, cliente.getTelefone());
			stmt.setString(6, cliente.getCidade());
			stmt.setDate(7, converteData(cliente.getNascimento()));
			stmt.setInt(8, cliente.getId());
			
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
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
		return dataSql;
	}
}