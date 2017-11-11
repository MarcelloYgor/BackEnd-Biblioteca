package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Teste {
	
	@Path("/teste")
	@GET
	public String teste() {
		return "teste";
	}
	
}
