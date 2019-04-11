package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.service.ProductoService;
import ar.edu.unq.tip.grupo6.app.webservice.dto.ProductoDTO;

@Component
@Path("/")
public class ProductoRest extends Rest {
	
	@Autowired
	private ProductoService productoService;
	
	@GET
	@Path("/productos")
	@Produces(APPLICATION_JSON)
	public Response getAllProductos() {
		return ok(productoService.getProductos().stream().map(ProductoDTO::new));
	}

}
