package ar.edu.unq.tip.grupo6.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nombre;
	Float precio;
	String imagen;
	
	public Producto(Integer id, String nombre, Float precio, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
	}

}
