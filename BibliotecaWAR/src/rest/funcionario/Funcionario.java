package rest.funcionario;

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

import dao.FuncionarioDAO;
import rest.authentication.Secured;

@Path("/funcionario")
public class Funcionario {

	private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	
	@Path("/adicionar")
	@PUT
	@Secured
	@Produces("application/json")
	public Response add(entity.Funcionario f) {
		ResponseBuilder builder = null;
		try {
			funcionarioDAO.cadastraFuncionario(f);
			builder = Response.ok("Ok");
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/alterar")
	@POST
	@Secured
	@Produces("application/json")
	public Response update(entity.Funcionario f) {
		ResponseBuilder builder = null;
		try {
			funcionarioDAO.alterarFuncionario(f);
			builder =Response.ok("Ok");
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/delete")
	@DELETE
	@Secured
	@Produces("application/json")
	public Response delete(@QueryParam("id") int id) {
		ResponseBuilder builder = null;
		try {
			funcionarioDAO.excluirFuncionario(id);
			builder =  Response.ok("Ok");
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/pesquisar/id")
	@POST
	@Secured
	@Produces("application/json")
	public Response pesquisaId(int id) {
		ResponseBuilder builder = null;
		entity.Funcionario funcionario = null;
		try {
			funcionario = funcionarioDAO.consultarFuncionarioId(id);
			builder = Response.ok(funcionario);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/pesquisar/nome")
	@POST
	@Secured
	@Produces("application/json")
	public Response pesquisaNome(String nome) {
		System.out.println(nome);
		entity.Funcionario funcionario = null;
		ResponseBuilder builder = null;
		try {
			funcionario = funcionarioDAO.consultarFuncionarioNome(nome);
			builder = Response.ok(funcionario);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/pesquisar")
	@GET
	@Secured
	@Produces("application/json")
	public Response pesquisaTodos() {
		System.out.println("todos");
		ArrayList<entity.Funcionario> funcionarios = null;
		ResponseBuilder builder = null;
		try {
			funcionarios = funcionarioDAO.consultarTodos();
			builder = Response.ok(funcionarios);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
}
