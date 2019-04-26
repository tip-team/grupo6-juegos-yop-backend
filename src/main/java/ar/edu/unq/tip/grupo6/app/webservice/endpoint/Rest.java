package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Optional;
import java.util.function.Function;

import javax.json.JsonObjectBuilder;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;
import ar.edu.unq.tip.grupo6.app.webservice.exception.NotFoundException;
import ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils;
import lombok.Getter;

public abstract class Rest {

	@Getter Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@OPTIONS
	@Path("{var:.+}" )
	@Produces(APPLICATION_JSON)
	public Response options() {
		return ok();
	}
	
	protected Response ok() {
		return RestUtils.ok();
	}
	
	protected <T> Response ok(T object) {
		return RestUtils.ok(object);
	}
	
	protected Response ok(Function<JsonObjectBuilder, JsonObjectBuilder> agregarValores) {
		return RestUtils.ok(agregarValores);
	}
	
	protected <T> T throwNullException(T object, String message) throws BadRequestException {
		return Optional.ofNullable(object).orElseThrow(() -> badRequestException(message));
	}
	
	protected void throwNullOrEmptyException(String object, String message) throws BadRequestException {
		if (throwNullException(object, message).isEmpty()) {
			badRequest(message);
		}
	}
	
	protected BadRequestException badRequestException(String message) {
		return new BadRequestException(message, logger);
	}
	
	protected void badRequest(String message) throws BadRequestException {
		throw new BadRequestException(message, logger);
	}
	
	protected void notFound(String message) throws NotFoundException {
		throw new NotFoundException(message, logger);
	}
	
}