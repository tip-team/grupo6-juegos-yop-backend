package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.badRequest;

@Provider
public class BadRequestExceptionHandler extends RestExceptionHandler implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException badRequest) {
		logError(badRequest.getLogger(), badRequest);
		return badRequest(badRequest.getMessage());
	}
	
}