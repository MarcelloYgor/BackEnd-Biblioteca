package rest.livro;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.AutorDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import entity.Livro;
import rest.authentication.Secured;

@Path("/livro")
public class LivroApi {
	
	private final LivroDAO livroDao = new LivroDAO();
	private final AutorDAO autorDao = new AutorDAO();
	private final EditoraDAO editoraDao = new EditoraDAO();
	private final GeneroDAO generoDao = new GeneroDAO();

	@Path("/adicionar")
	@PUT
	@Secured
	@Produces("application/json")
	public Response add(Livro livro) {
		Response.ResponseBuilder builder = null;
		try {
			// Autor editora genero
			livro.setAutor(autorDao.consultarAutorNome(livro.getAutor().getNome()));
			livro.setEditora(editoraDao.consultarEditoraNome(livro.getEditora().getNome()));
			livro.setGenero(generoDao.consultarGeneroNome(livro.getGenero().getNome()));
			Double random = Math.random();
			
			livro.setId(random.intValue());
			
			livroDao.cadastrarLivro(livro);

			builder = Response.ok("Ok");
		} catch (Exception e) {
			e.printStackTrace();
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/alterar")
	@POST
	@Secured
	@Produces("application/json")
	public ResponseBuilder update(Livro livro) {
		try {
			livroDao.alterarLivro(livro);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok("Ok");
	}
	
	@Path("/delete")
	@DELETE
	@Secured
	@Produces("application/json")
	public ResponseBuilder delete(@QueryParam("id") int id) {
		try {
			livroDao.excluirLivro(id);
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
