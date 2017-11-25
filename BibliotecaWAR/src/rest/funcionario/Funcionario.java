package rest.funcionario;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.FuncionarioDAO;
import rest.authentication.Secured;

@Path("/funcionario")
public class Funcionario {

	private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//
//	@Path("/adicionar")
//	@PUT
//	@Secured
//	@Produces("application/json")
//	public ResponseBuilder add(Livro livro) {
//		try {
//			funcionarioDAO.cadastrarLivro(livro);
//		} catch (Exception e) {
//			return Response.serverError();
//		}
//		return Response.ok("Ok");
//	}
//	
//	@Path("/alterar")
//	@POST
//	@Secured
//	@Produces("application/json")
//	public ResponseBuilder update(Livro livro) {
//		try {
//			livroDao.alterarLivro(livro);
//		} catch (Exception e) {
//			return Response.serverError();
//		}
//		return Response.ok("Ok");
//	}
//	
//	@Path("/delete")
//	@DELETE
//	@Secured
//	@Produces("application/json")
//	public ResponseBuilder delete(@QueryParam("id") int id) {
//		try {
//			livroDao.excluirLivro(id);
//		} catch (Exception e) {
//			return Response.serverError();
//		}
//		return Response.ok("Ok");
//	}
	
	@Path("/pesquisar")
	@POST
	@Secured
	@Produces("application/json")
	public ResponseBuilder pesquisaId(@QueryParam("id") int id) {
		entity.Funcionario funcionario = null;
		try {
			funcionario = funcionarioDAO.consultarFuncionarioId(id);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok(funcionario);
	}
	
//	@Path("/pesquisar")
//	@POST
//	@Secured
//	@Produces("application/json")
//	public ResponseBuilder pesquisaNome(@QueryParam("nome") String nome) {
//		Livro livro = null;
//		try {
//			livro = livroDao.consultarLivroNome(nome);
//		} catch (Exception e) {
//			return Response.serverError();
//		}
//		return Response.ok(livro);
//	}
//	
//	@Path("/pesquisar")
//	@GET
//	@Secured
//	@Produces("application/json")
//	public ResponseBuilder pesquisaTodos() {
//		ArrayList<Livro> livros = null;
//		try {
//			livros = livroDao.consultarLivroTodos();
//		} catch (Exception e) {
//			return Response.serverError();
//		}
//		return Response.ok(livros);
//	}
}
