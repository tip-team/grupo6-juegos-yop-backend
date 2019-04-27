package ar.edu.unq.tip.grupo6.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotNull(message = "El nombre debe estar definido.")
	@Size(min = 1, message = "El nombre debe estar definido.")
	String nombre;
	
	@NotNull(message="El precio debe estar definido.")
	Float precio;
	
	@NotNull(message = "La imágen debe estar definida.")
	@Size(min = 1, message = "La imágen debe estar definida.")
	String imagen;

}
