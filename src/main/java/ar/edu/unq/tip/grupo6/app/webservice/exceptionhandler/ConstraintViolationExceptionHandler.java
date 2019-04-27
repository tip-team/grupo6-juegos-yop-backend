package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils;

@Provider
public class ConstraintViolationExceptionHandler extends RestExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	Logger logger = LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);
	
	@Override
	public Response toResponse(ConstraintViolationException constraintViolationException) {
		String constraintViolationMessage = constraintViolationException.getConstraintViolations().stream().findFirst().get().getMessage();
		return RestUtils.badRequest(constraintViolationMessage);
	}
	
}

//public class ConstraintViolationExceptionHandler {
//
//}
