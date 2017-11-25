package rest.livro;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.LivroDAO;
import entity.Livro;
import rest.authentication.Secured;

@Path("/livro")
public class LivroApi {
	
	private final LivroDAO livroDao = new LivroDAO();

	@Path("/adicionar")
	@PUT
	@Secured
	@Produces("application/json")
	public ResponseBuilder add(Livro livro) {
		try {
			livroDao.cadastrarLivro(livro);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok("Ok");
	}
	
	@Path("/pesquisar")
	@POST
	@Secured
	@Produces("application/json")
	public ResponseBuilder pesquisaId(@QueryParam("id") int id) {
		Livro livro = null;
		try {
			livro = livroDao.consultarLivroId(id);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok(livro);
	}
	
	@Path("/pesquisar")
	@POST
	@Secured
	@Produces("application/json")
	public ResponseBuilder pesquisaNome(@QueryParam("nome") String nome) {
		Livro livro = null;
		try {
			livro = livroDao.consultarLivroNome(nome);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok(livro);
	}
	
	@Path("/pesquisar")
	@GET
	@Secured
	@Produces("application/json")
	public ResponseBuilder pesquisaTodos() {
		ArrayList<Livro> livros = null;
		try {
			livros = livroDao.consultarLivroTodos();
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok(livros);
	}
}
