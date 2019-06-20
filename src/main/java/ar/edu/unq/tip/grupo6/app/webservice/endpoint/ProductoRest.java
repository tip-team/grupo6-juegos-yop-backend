package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.service.ProductoService;
import ar.edu.unq.tip.grupo6.app.service.dto.ProductoPriorityDTO;
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
	
	@GET
	@Path("/productos/imagen/{id}")
	@Produces(MediaType.IMAGE_JPEG_VALUE)
	public Response getImagen(@PathParam("id") Integer id) {
		String imageDataBytes = productoService.getImagen(id).substring(productoService.getImagen(id).indexOf(",")+1);
		InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(imageDataBytes.getBytes()));
		return ok(stream);
	}
	
	@POST
	@Path("/productos")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response crearProducto(Producto producto) {
		Integer productoId = productoService.createProducto(producto);
		return ok(json -> json.add("id", productoId));
	}
	
	@GET
	@BadRequestId(message = "obtener la descripción de un producto")
	@Path("/productos/desc/{id}")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response getProductoDesc(@PathParam("id") Integer id) throws ProductoInexistenteException {
		String imagen = productoService.getProductoDesc(id);
		return ok(json -> json.add("imagenDesc", imagen));
	}

	@PUT
	@Path("/productos")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response updateProducto(Producto producto) {
		productoService.updateProducto(producto);
		return ok();
	}
	
	@PUT
	@Path("/productos/order")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response updateProductos(List<ProductoPriorityDTO> prioridades) {
		productoService.updatePrioridades(prioridades);
		return ok();
	}
	
	@DELETE
	@BadRequestId(message = "eliminar un producto")
	@Path("/productos/{id}")
	@Produces(APPLICATION_JSON)
	public Response borrarProducto(@PathParam("id") String id) throws BadRequestException, ProductoInexistenteException {
		productoService.borrarProducto(id);
		return ok();
	}
	
}