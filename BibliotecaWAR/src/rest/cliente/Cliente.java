package rest.cliente;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import dao.ClienteDAO;
import rest.authentication.Secured;

@Path("/cliente")
public class Cliente {

	private final ClienteDAO clienteDao = new ClienteDAO();
	
	@Path("/adicionar")
	@PUT
	@Secured
	@Produces("application/json")
	public Response add(entity.Cliente cliente) {
		Response.ResponseBuilder builder = null;
		try {
			clienteDao.cadastrarCliente(cliente);
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
	public Response update(entity.Cliente cliente) {
		Response.ResponseBuilder builder = null;
		try {
			clienteDao.alterarCliente(cliente);
			builder = Response.ok("Ok");
		} catch (Exception e) {
			builder=  Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/delete")
	@DELETE
	@Secured
	@Produces("application/json")
	public ResponseBuilder delete(@QueryParam("id") int id) {
		try {
			clienteDao.excluirCliente(id);
		} catch (Exception e) {
			return Response.serverError();
		}
		return Response.ok("Ok");
	}
	
	@Path("/pesquisar/id")
	@POST
	@Secured
	@Produces("application/json")
	public Response pesquisaId(@QueryParam("id") int id) {
		entity.Cliente cliente = null;
		Response.ResponseBuilder builder = null;
		try {
			cliente = clienteDao.consultarClienteId(id);
			builder = Response.ok(cliente);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
	
	@Path("/pesquisar/cpf")
	@POST
	@Secured
	@Produces("application/json")
	public Response pesquisaNome(Long cpf) {
		entity.Cliente cliente = null;
		Response.ResponseBuilder builder = null;
		try {
			System.out.println(cpf);
			cliente = clienteDao.consultarClienteCpf(cpf);
			builder = Response.ok(cliente);
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
		List<entity.Cliente> clientes = null;
		Response.ResponseBuilder builder = null;
		try {
			clientes = clienteDao.pegarClientes();
			builder = Response.ok(clientes);
		} catch (Exception e) {
			builder = Response.serverError();
		}
		return builder.build();
	}
}
