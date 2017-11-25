package rest.authentication;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.model.Credenciais;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/authentication")
public class Autenticacao {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credenciais credenciais) {
		try {
			authenticate(credenciais.getUsername(), credenciais.getPassword());

			String token = issueToken(credenciais.getUsername());

			return Response.ok(token).build();

		} catch (Exception e) {
			System.out.println(e);
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		// Busca no banco de dados
	}

	private String issueToken(String username) {
		return Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, "Meu_Segerdo").compact();
	}

}
