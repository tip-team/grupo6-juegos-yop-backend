package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.service.ProductoService;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;
import ar.edu.unq.tip.grupo6.app.webservice.annotation.BadRequestId;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;

@Component
@Path("/")
public class ProductoRest extends Rest {
	
	@Autowired
	private ProductoService productoService;
	
	@GET
	@Path("/productos")
	@Produces(APPLICATION_JSON)
	public Response getAllProductos() {
		return ok(productoService.getProductos());
	}
	
	@POST
	@Path("/productos")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response crearProducto(Producto producto) {
		productoService.createProducto(producto);
		return ok();
	}
	
	@DELETE
	@BadRequestId(message = "eliminar un producto")
	@Path("/productos/{id}")
	@Produces(APPLICATION_JSON)
	public Response borrarProducto(@PathParam("id") String id) throws BadRequestException {
		try {
			productoService.borrarProducto(Integer.valueOf(id));
		} catch (ProductoInexistenteException exception) {
			badRequest(exception.getMessage());
		}
		return ok();
	}
	
}