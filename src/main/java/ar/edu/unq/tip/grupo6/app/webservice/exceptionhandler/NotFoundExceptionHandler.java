package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ar.edu.unq.tip.grupo6.app.webservice.exception.NotFoundException;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.notFound;

@Provider
public class NotFoundExceptionHandler extends RestExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException notFoundException) {
		logError(notFoundException.getLogger(), notFoundException);
		return notFound(notFoundException.getMessage());
	}
	
}