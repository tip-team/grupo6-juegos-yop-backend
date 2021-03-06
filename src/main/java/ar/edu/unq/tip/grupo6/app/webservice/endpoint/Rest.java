package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import java.util.function.Function;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;
import ar.edu.unq.tip.grupo6.app.webservice.exception.NotFoundException;
import ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils;
import lombok.Getter;

public abstract class Rest {

	@Getter Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Response ok() {
		return RestUtils.ok();
	}
	
	protected <T> Response ok(T object) {
		return RestUtils.ok(object);
	}
	
	protected Response ok(Function<JsonObjectBuilder, JsonObjectBuilder> agregarValores) {
		return RestUtils.ok(agregarValores);
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