package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils;

public class ServerExceptionHandler extends RestExceptionHandler implements ExceptionMapper<Exception> {

	Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);
	
	@Override
	public Response toResponse(Exception exception) {
		logError(logger, exception);
		return RestUtils.internalServerError("Hubo en error al invocar el servicio.");
	}
	
}