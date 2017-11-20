package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/teste")
public class Teste {
	
	@Path("/")
	@GET
	@Secured
	@Produces("application/json")
	public String teste() {
		String retorno = "{\"ok\": \"funciona\"}";
		return retorno;
	}
}
