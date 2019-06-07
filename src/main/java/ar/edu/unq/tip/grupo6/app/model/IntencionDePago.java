package ar.edu.unq.tip.grupo6.app.model;

import javax.validation.constraints.NotNull;
import ar.edu.unq.tip.grupo6.app.model.validation.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IntencionDePago {

	@NotNull(message = "El id del producto debe estar definido.")
	private String id;
	
	@NotNull(message = "El email debe estar definido.")
	@Email(message = "Email invalido.")
	private String email;
	
	@NotNull(message = "El teléfono debe estar definido.")
	private String telefono;
	
	@NotNull(message = "El nombre debe estar definido.")
	private String nombre;
	
}
