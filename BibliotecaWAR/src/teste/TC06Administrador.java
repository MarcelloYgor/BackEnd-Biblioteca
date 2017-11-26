package teste;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AdministradorDAO;
import entity.Administrador;

public class TC06Administrador {

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
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
		admDAO.excluirAdministrador(administrador.getId());
	}

	@Test
	public void cadastrarAdm() {
		admDAO = new AdministradorDAO();
		admDAO.cadastrarAdministrador(administrador);
	}
	
	@Test
	public void alterarAdm() {
		admDAO = new AdministradorDAO();
		admDAO.cadastrarAdministrador(administrador);
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
		administrador.setNome("Igão de Souza Silva");
		admDAO.alterarAdministrador(administrador);
	}
	
	@Test
	public void consultarAdm() {
		admDAO = new AdministradorDAO();
		admDAO.cadastrarAdministrador(administrador);
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
	}
}
