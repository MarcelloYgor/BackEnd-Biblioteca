package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AdministradorDAO;
import dao.FuncionarioDAO;
import entity.Administrador;
import entity.Funcionario;

public class TC05Funcionario {

	private Funcionario funcionario;
	private FuncionarioDAO funDAO;
	private Administrador administrador;
	private AdministradorDAO admDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		administrador = new Administrador();
		administrador.setNome("Igão de Souza");
		administrador.setRg("123456789");
		administrador.setCpf(12345678910L);
		administrador.setSalario(2500.00);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		admDAO.excluirAdministrador(funcionario.getId());
	}

	@Test
	public void consultarFuncinario() {
		admDAO = new AdministradorDAO();
		funDAO = new FuncionarioDAO();
		admDAO.cadastrarAdministrador(administrador);
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
		funcionario = new Funcionario();
		funcionario = funDAO.consultarFuncionarioId(administrador.getId());
	}
}
