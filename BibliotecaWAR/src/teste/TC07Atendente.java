package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AtendenteDAO;
import entity.Atendente;

public class TC07Atendente {

	private Atendente atendente;
	private AtendenteDAO atdDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		atendente = new Atendente();
		atendente.setNome("Igão de Souza");
		atendente.setRg("123456789");
		atendente.setCpf(12345678910L);
		atendente.setSalario(2500.00);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		atendente = atdDAO.consultarAtendenteCpf(atendente.getCpf());
		atdDAO.excluirAtendente(atendente.getId());
	}

	@Test
	public void cadastrarAdm() {
		atdDAO = new AtendenteDAO();
		atdDAO.cadastrarAtendente(atendente);
	}
	
	@Test
	public void alterarAdm() {
		atdDAO = new AtendenteDAO();
		atdDAO.cadastrarAtendente(atendente);
		atendente = atdDAO.consultarAtendenteCpf(atendente.getCpf());
		atendente.setNome("Igão de Souza Silva");
		atdDAO.alterarAtendente(atendente);
	}
	
	@Test
	public void consultarAdm() {
		atdDAO = new AtendenteDAO();
		atdDAO.cadastrarAtendente(atendente);
		atendente = atdDAO.consultarAtendenteCpf(atendente.getCpf());
	}
}
