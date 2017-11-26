package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.ClienteDAO;
import entity.Cliente;

public class TC01Cliente {

	private Cliente cliente;
	private ClienteDAO cDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		cliente = new Cliente();
		cliente.setNome("J�o Alegre");
		cliente.setRg("12345678");
		cliente.setCpf(12345678910L);
		cliente.setEndereco("Rua da Tristeza, 9000 - Bairro Limoeiro");
		cliente.setTelefone(11999999999L);
		cliente.setCidade("S�o Pedro de Pataj�s");
		java.util.Date dataAtual = new java.util.Date();
		cliente.setNascimento(dataAtual);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		cliente = cDAO.consultarClienteCpf(cliente.getCpf());
		cDAO.excluirCliente(cliente.getId());
	}

	@Test
	public void cadastrarCliente() {
		cDAO = new ClienteDAO();
		cDAO.cadastrarCliente(cliente);
	}
	
	@Test
	public void consultarCliente() {
		cDAO = new ClienteDAO();
		cDAO.cadastrarCliente(cliente);
		cliente = cDAO.consultarClienteCpf(cliente.getCpf());
		System.out.println(cliente.getId());
		System.out.println(cliente.getNome());
	}
	
	@Test
	public void alterarCliente() {
		cDAO = new ClienteDAO();
		cDAO.cadastrarCliente(cliente);
		cliente = cDAO.consultarClienteCpf(cliente.getCpf());
		cliente.setNome("J�o Tristonho");
		cDAO.alterarCliente(cliente);
		cliente = cDAO.consultarClienteCpf(cliente.getCpf());
		System.out.println(cliente.getId() + " Nome: " + cliente.getNome());
	}
}
