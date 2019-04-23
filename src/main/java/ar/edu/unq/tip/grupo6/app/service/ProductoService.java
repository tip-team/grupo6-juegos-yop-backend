package ar.edu.unq.tip.grupo6.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.repository.ProductoRepository;

@Component
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public List<Producto> getProductos() {
		return productoRepository.findAll();
	}

	public List<Producto> getProducto(Producto producto) {
		Optional<Producto> productoFounded = productoRepository.findById(producto.getId());
		return Arrays.asList(productoFounded.get());
	}

	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	public void borrarProducto(Integer id) {
		productoRepository.deleteById(id);
	}

}
