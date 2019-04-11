package ar.edu.unq.tip.grupo6.app.webservice.dto;

import ar.edu.unq.tip.grupo6.app.model.Producto;
import lombok.Getter;

@Getter
public class ProductoDTO {
	
	String nombre;
	Float precio;
	String image;
	
	public ProductoDTO(Producto producto) {
		nombre = producto.getNombre();
		precio = producto.getPrecio();
		image = producto.getImage();
	}
	
}
