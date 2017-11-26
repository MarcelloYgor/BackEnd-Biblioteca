package teste;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AdministradorDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import entity.Administrador;
import entity.Funcionario;
import entity.Login;

public class TC08Login {

	private Login login;
	private LoginDAO logDAO;
	private Administrador administrador;
	private AdministradorDAO admDAO;
	private FuncionarioDAO funDAO;
	private Funcionario funcionario;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		login = new Login();
		login.setEmail("funcionario@biblioteca.com");
		login.setPassword("senha");
		administrador = new Administrador();
		administrador.setNome("Ig�o de Souza");
		administrador.setRg("123456789");
		administrador.setCpf(12345678910L);
		administrador.setSalario(2500.00);
		admDAO = new AdministradorDAO();
		admDAO.cadastrarAdministrador(administrador);
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
		login.setFuncionario(funDAO.consultarFuncionarioId(administrador.getId()));
		login.setAdmin(funcionario.isAdmin());
	}

	@After
	public void tearDownAfterClass() throws Exception {
		login = logDAO.consultarLoginEmail(login.getEmail());
		logDAO.excluirLogin(login.getId());
	}

	@Test
	public void cadastrarLogin() {
		logDAO = new LoginDAO();
		logDAO.cadastrarLogin(login);
	}
	
	@Test
	public void alterarLogin() {
		logDAO = new LoginDAO();
		logDAO.cadastrarLogin(login);
		try {
			login = logDAO.consultarLoginEmail(login.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.setPassword("senha123");
		logDAO.alterarLogin(login);
	}
	
	@Test
	public void consultarLogin() {
		logDAO = new LoginDAO();
		logDAO.cadastrarLogin(login);
		try {
			login = logDAO.consultarLoginEmail(login.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
