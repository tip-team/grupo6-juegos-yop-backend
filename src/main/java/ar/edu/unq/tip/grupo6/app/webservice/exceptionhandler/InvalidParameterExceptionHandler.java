package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import ar.edu.unq.tip.grupo6.app.service.exception.InvalidParameterException;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.badRequest;

@Provider
public class InvalidParameterExceptionHandler extends RestExceptionHandler implements ExceptionMapper<InvalidParameterException> {

	@Override
	public Response toResponse(InvalidParameterException exception) {
		return badRequest(exception.getMessage());
	}

}