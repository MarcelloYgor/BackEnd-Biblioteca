package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.GeneroDAO;
import entity.Genero;

public class TC04Genero {

	private Genero genero;
	private GeneroDAO genDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		genero = new Genero();
		genero.setNome("Ação");
		genero.setDescricao("Tiro, porrada e bomba");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		genero = genDAO.consultarGeneroNome(genero.getNome());
		genDAO.excluirGenero(genero.getId());
	}

	@Test
	public void cadastrarGenero() {
		genDAO = new GeneroDAO();
		genDAO.cadastrarGenero(genero);
	}
	
	@Test
	public void alterarGenero() {
		genDAO = new GeneroDAO();
		genDAO.cadastrarGenero(genero);
		genero = genDAO.consultarGeneroNome(genero.getNome());
		genero.setDescricao("Tiro, porrada, bomba e explosão");
		genDAO.alterarGenero(genero);
	}
	
	@Test
	public void consultarGenero() {
		genDAO = new GeneroDAO();
		genDAO.cadastrarGenero(genero);
		genero = genDAO.consultarGeneroNome(genero.getNome());
	}
}
