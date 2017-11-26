package teste;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AdministradorDAO;
import dao.AutorDAO;
import dao.ClienteDAO;
import dao.EditoraDAO;
import dao.EmprestimoDAO;
import dao.FuncionarioDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import dao.LivrosEmprestados;
import entity.Administrador;
import entity.Autor;
import entity.Cliente;
import entity.Editora;
import entity.Emprestimo;
import entity.Funcionario;
import entity.Genero;
import entity.Livro;

public class TC10Emprestimo {

	private Autor autor;
	private AutorDAO autDAO;
	private Editora editora;
	private EditoraDAO edDAO;
	private Genero genero;
	private GeneroDAO genDAO;
	private Cliente cliente;
	private ClienteDAO cliDAO;
	private Funcionario funcionario;
	private FuncionarioDAO funDAO;
	private Administrador administrador;
	private AdministradorDAO admDAO;
	private Livro livro;
	private LivroDAO livDAO;
	private List<Livro> livros;

	private Emprestimo emprestimo;
	private EmprestimoDAO emDAO;
	
	private LivrosEmprestados lvEmp;

	@Before
	public void setUpBeforeClass() throws Exception {
		cadastraAutor();
		cadastraEditora();
		cadastraGenero();
		cadastraCliente();
		cadastraFuncionario();
		cadastraLivro();
		emprestimo = new Emprestimo();
		emprestimo.setLivros(livros);
		emprestimo.setCliente(cliente);
		emprestimo.setFuncionario(funcionario);
		java.util.Date dataAtual = new java.util.Date();
		emprestimo.setDataEmprestimo(dataAtual);
		emprestimo.setDataDevolucao(null);
		emprestimo.setActive(true);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		lvEmp.excluirLivrosEmprestados(emprestimo.getId());
		emDAO.excluirEmprestimo(emprestimo.getId());
		for (Livro livrao : livros) {
			livDAO.excluirLivro(livrao.getId());
		}
		admDAO.excluirAdministrador(administrador.getId());
		cliDAO.excluirCliente(cliente.getId());
		genDAO.excluirGenero(genero.getId());
		edDAO.excluirEditora(editora.getId());
		autDAO.excluirAutor(autor.getId());
	}

	@Test
	public void cadastrarEmprestimo() {
		emDAO = new EmprestimoDAO();
		emDAO.cadastrarEmprestimo(emprestimo);
		emprestimo = emDAO.consultarEmprestimo(emprestimo.getCliente().getId(), emprestimo.getFuncionario().getId(), emprestimo.getDataEmprestimo());
		lvEmp = new LivrosEmprestados();
		lvEmp.cadastrarLivrosEmprestados(livros, emprestimo.getId());
	}

	@Test
	public void alterarEmprestimo() {
		emDAO = new EmprestimoDAO();
		emDAO.cadastrarEmprestimo(emprestimo);
		emprestimo = emDAO.consultarEmprestimo(emprestimo.getCliente().getId(), emprestimo.getFuncionario().getId(), emprestimo.getDataEmprestimo());
		lvEmp = new LivrosEmprestados();
		lvEmp.cadastrarLivrosEmprestados(livros, emprestimo.getId());
		emprestimo.setActive(false);
		emDAO.alterarEmprestimo(emprestimo);
	}

	@Test
	public void consultarEmprestimo() {
		emDAO = new EmprestimoDAO();
		emDAO.cadastrarEmprestimo(emprestimo);
		emprestimo = emDAO.consultarEmprestimo(emprestimo.getCliente().getId(), emprestimo.getFuncionario().getId(), emprestimo.getDataEmprestimo());
		lvEmp = new LivrosEmprestados();
		lvEmp.cadastrarLivrosEmprestados(livros, emprestimo.getId());
		//Consulta
		emprestimo = emDAO.consultarEmprestimo(emprestimo.getCliente().getId(), emprestimo.getFuncionario().getId(), emprestimo.getDataEmprestimo());
		livros = lvEmp.pegarLivrosEmprestados(emprestimo.getId());
		emprestimo.setLivros(livros);
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

	private void cadastraCliente() {
		cliente = new Cliente();
		cliente.setNome("Jão Alegre");
		cliente.setRg("12345678");
		cliente.setCpf(12345678910L);
		cliente.setEndereco("Rua da Tristeza, 9000 - Bairro Limoeiro");
		cliente.setTelefone(11999999999L);
		cliente.setCidade("São Pedro de Patajós");
		java.util.Date dataAtual = new java.util.Date();
		cliente.setNascimento(dataAtual);
		cliDAO = new ClienteDAO();
		cliDAO.cadastrarCliente(cliente);
		cliente = cliDAO.consultarClienteCpf(cliente.getCpf());
	}

	private void cadastraFuncionario() {
		administrador = new Administrador();
		administrador.setNome("Igão de Souza");
		administrador.setRg("123456789");
		administrador.setCpf(12345678910L);
		administrador.setSalario(2500.00);
		admDAO = new AdministradorDAO();
		funDAO = new FuncionarioDAO();
		admDAO.cadastrarAdministrador(administrador);
		administrador = admDAO.consultarAdministradorCpf(administrador.getCpf());
		funcionario = new Funcionario();
		funcionario = funDAO.consultarFuncionarioId(administrador.getId());
	}

	private void cadastraLivro() {
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
		livDAO = new LivroDAO();
		livDAO.cadastrarLivro(livro);
		livro = livDAO.consultarLivroNome(livro.getNome());
		livros = new ArrayList<>();
		livros.add(livro);
		livro.setNome("Lupa Lupas");
		livro.setAutor(autor);
		livro.setEditora(editora);
		livro.setAno(2005);
		livro.setEdicao(2);
		livro.setNumPaginas(777);
		livro.setGenero(genero);
		livro.setIdioma("Portugays");
		livDAO.cadastrarLivro(livro);
		livro = livDAO.consultarLivroNome(livro.getNome());
		livros.add(livro);
	}
}
