package ar.edu.unq.tip.grupo6.app.model;

import javax.validation.constraints.NotNull;
import ar.edu.unq.tip.grupo6.app.model.validation.Email;
import lombok.Getter;

@Getter
public class Mail {
	
	@NotNull(message = "El remitente debe estar definido.")
	@Email(message = "El remitente debe tener una dirección de correo válida.")
	private String remitente;
	
	@NotNull(message = "El nombre debe estar definido.")
	private String nombre;
	
	@NotNull(message = "El asunto debe estar definido.")
	private String asunto;
	
	@NotNull(message = "El cuerpo debe estar definido.")
	private String cuerpo;

}
