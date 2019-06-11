package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Mail;
import ar.edu.unq.tip.grupo6.app.service.EmailService;
import ar.edu.unq.tip.grupo6.app.service.exception.InvalidParameterException;

@Component
@Path("/email")
public class EmailRest extends Rest {

	@Autowired
	private EmailService emailService;
	
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response sendEmail(Mail mail) throws InvalidParameterException {
		emailService.send(mail);
		return ok();
	}
	
}
