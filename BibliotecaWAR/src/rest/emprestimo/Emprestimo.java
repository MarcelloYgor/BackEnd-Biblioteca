package rest.emprestimo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.EmprestimoDAO;
import dao.LivrosEmprestados;
import entity.Livro;
import rest.authentication.Secured;
import rest.emprestimo.vo.EmprestimoVO;

@Path("/emprestimo")
public class Emprestimo {
	
	private EmprestimoDAO emprestimoDao = new EmprestimoDAO();
	private LivrosEmprestados livrosEmprestadosDao = new LivrosEmprestados();
	
	@Path("/emprestados")
	@POST
	@Secured
	@Consumes("application/json")
	@Produces("application/json")
	public Response emprestados() {
		ResponseBuilder builder = null;
		try {
			 List<Livro> emprestimos = livrosEmprestadosDao.pegarLivrosEmprestados();
			builder = Response.ok(emprestimos);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}

	@Path("/emprestar")
	@POST
	@Secured
	@Consumes("application/json")
	@Produces("application/json")
	public Response emprestimo(EmprestimoVO emprestimo) {
		ResponseBuilder builder = null;
		try {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 100000 + 1);
			entity.Emprestimo emprestimoE = new entity.Emprestimo();
			emprestimoE.setCliente(emprestimo.getCliente());
			emprestimoE.setLivros(emprestimo.getLivro());
			emprestimoE.setDataEmprestimo(emprestimo.getDt_emprestimo());
			emprestimoE.setDataDevolucao(emprestimo.getDt_devolucao());
			emprestimoE.setActive(true);
			emprestimoE.setId(randomNum);
			
			emprestimoDao.cadastrarEmprestimo(emprestimoE);
			livrosEmprestadosDao.cadastrarLivrosEmprestados(emprestimo.getLivro(), randomNum);
			
			builder = Response.ok("{'ok':200}");
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
}
