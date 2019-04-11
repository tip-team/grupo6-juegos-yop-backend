package ar.edu.unq.tip.grupo6.app.webservice.dto;

import ar.edu.unq.tip.grupo6.app.model.Producto;
import lombok.Getter;

@Getter
public class ProductoDTO {
	
	Integer id;
	String nombre;
	Float precio;
	String imagen;
	
	public ProductoDTO(Producto producto) {
		id = producto.getId();
		nombre = producto.getNombre();
		precio = producto.getPrecio();
		imagen = producto.getImagen();
	}
	
}
