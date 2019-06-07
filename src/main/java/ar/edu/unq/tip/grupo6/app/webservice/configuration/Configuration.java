package ar.edu.unq.tip.grupo6.app.webservice.configuration;

import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.BadRequestExceptionHandler;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.ConstraintViolationExceptionHandler;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.InvalidParameterExceptionHandler;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.NotFoundExceptionHandler;
import ar.edu.unq.tip.grupo6.app.webservice.exceptionhandler.ServerExceptionHandler;

@Component
@ApplicationPath("/api")
public class Configuration extends ResourceConfig {
	
	public Configuration() {
		scan("ar.edu.unq.tip.grupo6.app.webservice.endpoint");
	}
	
	private void scan(String... packages) {
		Stream.of(packages).forEach(pack -> registerPackage(pack));
	}
	
	private void registerPackage(String pack) {
		Reflections reflections = new Reflections(pack);
		reflections.getTypesAnnotatedWith(Path.class).stream().forEach(clazz -> register(clazz));
		register(BadRequestExceptionHandler.class);
		register(ServerExceptionHandler.class);
		register(NotFoundExceptionHandler.class);
		register(ConstraintViolationExceptionHandler.class);
		register(InvalidParameterExceptionHandler.class);
	}

}