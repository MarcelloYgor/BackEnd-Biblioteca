package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AutorDAO;
import entity.Autor;

public class TC03Autor {

	private Autor autor;
	private AutorDAO autDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		autor = new Autor();
		autor.setNome("Lucas Pereira");
		autor.setNacionalidade("Brasuka");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		autor = autDAO.consultarAutorNome(autor.getNome());
		autDAO.excluirAutor(autor.getId());
	}

	@Test
	public void cadastrarAutor() {
		autDAO = new AutorDAO();
		autDAO.cadastrarAutor(autor);
	}
	
	@Test
	public void alterarAutor() {
		autDAO = new AutorDAO();
		autDAO.cadastrarAutor(autor);
		autor = autDAO.consultarAutorNome(autor.getNome());
		autor.setNacionalidade("Brasukeiro");
		autDAO.alterarAutor(autor);
	}
	
	@Test
	public void consultarAutor() {
		autDAO = new AutorDAO();
		autDAO.cadastrarAutor(autor);
		autor = autDAO.consultarAutorNome(autor.getNome());
	}
}
