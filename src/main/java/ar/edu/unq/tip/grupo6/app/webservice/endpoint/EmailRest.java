package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unq.tip.grupo6.app.service.EmailService;
import ar.edu.unq.tip.grupo6.app.webservice.annotation.BadRequestId;

@Component
@Path("/email")
public class EmailRest extends Rest {

	@Autowired
	private EmailService emailService;
	
	@POST
	@BadRequestId(message = "no se pudo enviar el email")
	@Path("/send")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response sendEmail(@QueryParam("from") String from, @QueryParam("name") String name, @QueryParam("asunto") String asunto, @QueryParam("msg") String msg ) throws IOException {
		emailService.send(from, name, asunto, msg);
		return ok();
	}
	
}
