package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.notFound;

@Provider
public class ProductoInexistenteExceptionHandler extends RestExceptionHandler implements ExceptionMapper<ProductoInexistenteException> {

	@Override
	public Response toResponse(ProductoInexistenteException productoInexistenteException) {
		return notFound(productoInexistenteException.getMessage());
	}
	
}