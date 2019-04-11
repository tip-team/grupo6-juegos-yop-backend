package ar.edu.unq.tip.grupo6.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import ar.edu.unq.tip.grupo6.app.model.Producto;

@Component
public class ProductoService {

	private List<Producto> productos;
	
	public ProductoService() {
		productos = new ArrayList<Producto>(Arrays.asList(
				new Producto("Sube y baja", 5500f, "https://bit.ly/2TA2Dsa"),
				new Producto("Tobogán", 5500f, "https://bit.ly/2KacAgG"),
				new Producto("Mecedor", 5500f, "https://bit.ly/2YLL64b"),
				new Producto("Mesa y silla", 5500f, "https://bit.ly/2CS8bJ1"),
				new Producto("Mesa y silla jardín de infantes", 5500f, "https://bit.ly/2FSXsA2"),
				new Producto("Calesita", 5500f, "https://bit.ly/2FSXwQi")
			));
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
}
