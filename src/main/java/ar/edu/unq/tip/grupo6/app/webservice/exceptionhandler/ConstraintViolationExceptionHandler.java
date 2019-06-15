package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.badRequest;

@Provider
public class ConstraintViolationExceptionHandler extends RestExceptionHandler implements ExceptionMapper<ConstraintViolationException> {	
	
	@Override
	public Response toResponse(ConstraintViolationException constraintViolationException) {
		String constraintViolationMessage = constraintViolationException.getConstraintViolations().stream().findFirst().get().getMessage();
		return badRequest(constraintViolationMessage);
	}
	
}
