package ar.edu.unq.tip.grupo6.app.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import ar.edu.unq.tip.grupo6.app.service.annotation.Valid;
import ar.edu.unq.tip.grupo6.app.service.exception.InvalidParameterException;
import ar.edu.unq.tip.grupo6.app.util.ConfigurationLoader;
import lombok.SneakyThrows;

@Component
public class EmailService {

	@Valid
	public void send(ar.edu.unq.tip.grupo6.app.model.Mail mail) throws InvalidParameterException {
		Email remitente = new Email(mail.getRemitente());
	    Email destinatario = new Email(ConfigurationLoader.JUEGOS_YOP_EMAIL);
	    Content contenido = new Content("text/plain", mail.getCuerpo());
	    Mail sendgridEmail = new Mail(remitente, mail.getAsunto(), destinatario, contenido);
	    SendGrid sendgrid = new SendGrid(ConfigurationLoader.JUEGOS_YOP_EMAIL_API_KEY);
		Request request = buildEmail(sendgridEmail);
		CompletableFuture.runAsync(() -> sendEmail(sendgrid, request));
	}

	@SneakyThrows
	private void sendEmail(SendGrid sendgrid, Request request) {
		sendgrid.api(request);
	}

	@SneakyThrows
	private Request buildEmail(Mail sendgridEmail) {
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(sendgridEmail.build());
		return request;
	}

}
