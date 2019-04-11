package ar.edu.unq.tip.grupo6.app.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ar.edu.unq.tip.grupo6.app.model.Producto;

@Component
public class ProductoRepository {
	
	private List<Producto> productos;
	
	public ProductoRepository() {
		productos = new ArrayList<Producto>(Arrays.asList(
				new Producto(1, "Sube y baja", 5500f, "https://bit.ly/2TA2Dsa"),
				new Producto(2, "Tobogán", 5500f, "https://bit.ly/2KacAgG"),
				new Producto(3, "Mecedor", 5500f, "https://bit.ly/2YLL64b"),
				new Producto(4, "Mesa y silla", 5500f, "https://bit.ly/2CS8bJ1"),
				new Producto(5, "Mesa y silla jardín de infantes", 5500f, "https://bit.ly/2FSXsA2"),
				new Producto(6, "Calesita", 5500f, "https://bit.ly/2FSXwQi")
			));
	}
	
	public List<Producto> findAll() {
		return productos;
	}
	
	public Optional<Producto> findById(Integer id) {
		return productos.stream().filter(producto -> producto.getId().equals(id)).findFirst();
	}

}
