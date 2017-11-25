package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.EditoraDAO;
import entity.Editora;

public class TC02Editora {

	private Editora editora;
	private EditoraDAO edDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		editora = new Editora();
		editora.setNome("Atlanta");
		editora.setEndereco("Rua Atlântica, 877 - Bairro dos Vales");
		editora.setNacionalidade("Francesa");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		editora = edDAO.consultarEditoraNome(editora.getNome());
		edDAO.excluirEditora(editora.getId());
	}

	@Test
	public void cadastrarEditora() {
		edDAO = new EditoraDAO();
		edDAO.cadastrarEditora(editora);
	}
	
	@Test
	public void alterarEditora() {
		edDAO = new EditoraDAO();
		edDAO.cadastrarEditora(editora);
		editora.setNome("Atlântica");
		edDAO.alterarEditora(editora);
	}
	
	@Test
	public void consultarEditora() {
		edDAO = new EditoraDAO();
		edDAO.cadastrarEditora(editora);
		edDAO.consultarEditoraNome(editora.getNome());
	}
}
