package ar.edu.unq.tip.grupo6.app.service;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import ar.edu.unq.tip.grupo6.app.util.ConfigurationLoader;

@Component
public class EmailService {

	public void send(String email, String name, String subject, String msg) throws IOException {
		Email from = new Email(email);
	    Email to = new Email(ConfigurationLoader.JUEGOS_YOP_EMAIL);
	    Content content = new Content("text/plain", msg);
	    Mail mail = new Mail(from, subject, to, content);
	    SendGrid sg = new SendGrid(ConfigurationLoader.JUEGOS_YOP_EMAIL_API_KEY);
	    Request request = new Request();
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    request.setBody(mail.build());
	    sg.api(request);
	}

}
