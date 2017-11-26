package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AutorDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import entity.Autor;
import entity.Editora;
import entity.Genero;
import entity.Livro;

public class TC09Livro {

	private Autor autor;
	private AutorDAO autDAO;
	private Editora editora;
	private EditoraDAO edDAO;
	private Genero genero;
	private GeneroDAO genDAO;
	
	private Livro livro;
	private LivroDAO livDAO;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		cadastraAutor();
		cadastraEditora();
		cadastraGenero();
		livro = new Livro();
		livro.setNome("Chupa Cabra");
		livro.setAutor(autor);
		livro.setEditora(editora);
		livro.setAno(2010);
		livro.setEdicao(8);
		livro.setNumPaginas(666);
		livro.setGenero(genero);
		livro.setIdioma("Portugays");
		Livro.setQtdDisponivel(15);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		livro = livDAO.consultarLivroNome(livro.getNome());
		livDAO.excluirLivro(livro.getId());
		autDAO.excluirAutor(autor.getId());
		edDAO.excluirEditora(editora.getId());
		genDAO.excluirGenero(genero.getId());
	}

	@Test
	public void cadastrarLivro() {
		livDAO = new LivroDAO();
		livDAO.cadastrarLivro(livro);
	}
	
	@Test
	public void alterarLivro() {
		livDAO = new LivroDAO();
		livDAO.cadastrarLivro(livro);
		livro = livDAO.consultarLivroNome(livro.getNome());
		livro.setNome("Chapa Cabra");
		livDAO.alterarLivro(livro);
	}
	
	@Test
	public void consultarLivro() {
		livDAO = new LivroDAO();
		livDAO.cadastrarLivro(livro);
		livro = livDAO.consultarLivroNome(livro.getNome());
	}

	private void cadastraGenero() {
		genero = new Genero();
		genero.setNome("Ação");
		genero.setDescricao("Tiro, porrada e bomba");
		genDAO = new GeneroDAO();
		genDAO.cadastrarGenero(genero);
		genero = genDAO.consultarGeneroNome(genero.getNome());
	}

	private void cadastraEditora() {
		editora = new Editora();
		editora.setNome("Atlanta");
		editora.setEndereco("Rua Atlântica, 877 - Bairro dos Vales");
		editora.setNacionalidade("Francesa");
		edDAO = new EditoraDAO();
		edDAO.cadastrarEditora(editora);
		editora = edDAO.consultarEditoraNome(editora.getNome());
	}

	private void cadastraAutor() {
		autor = new Autor();
		autor.setNome("Lucas Pereira");
		autor.setNacionalidade("Brasuka");
		autDAO = new AutorDAO();
		autDAO.cadastrarAutor(autor);
		autor = autDAO.consultarAutorNome(autor.getNome());
	}
}
