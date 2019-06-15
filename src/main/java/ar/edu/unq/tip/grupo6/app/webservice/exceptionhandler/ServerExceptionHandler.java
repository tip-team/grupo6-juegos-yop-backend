package ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler;

import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils.internalServerError;

@Provider
public class ServerExceptionHandler extends RestExceptionHandler implements ExceptionMapper<Exception> {

	Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);
	
	@Override
	public Response toResponse(Exception exception) {
		if(exception instanceof WebApplicationException) {
			WebApplicationException webApplicationException = ((WebApplicationException) exception);
			logger.error(webApplicationException.getMessage(), exception);
			return webApplicationException.getResponse();
		}
		logError(logger, exception);
		return internalServerError("Hubo en error al invocar el servicio.");
	}
	
}