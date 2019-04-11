package ar.edu.unq.tip.grupo6.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Producto {
	
	String nombre;
	Float precio;
	String image;
	Integer cantidad;
	
	public Producto(String nombre, Float precio, String image) {
		setAllArgs(nombre, precio, image, 1);
	}
	
	public Producto(String nombre, Float precio, String image, Integer cantidad) {
		setAllArgs(nombre, precio, image, cantidad);
	}
	
	private void setAllArgs(String nombre, Float precio, String image, Integer cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.image = image;
		this.cantidad = cantidad;
	}

}
