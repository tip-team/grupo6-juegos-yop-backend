package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/productos")
public class Producto extends Rest {

	@GET
	public Response getProductos() {
		return ok();
	}
	
}
