package ar.edu.unq.tip.grupo6.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.repository.ProductoRepository;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;

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

	public Integer createProducto(@Valid Producto producto) {
		return productoRepository.save(producto).getId();
	}

	public void updateProducto(@Valid Producto producto) {
		Validator.validate(producto);
		productoRepository.save(producto);
	}

	public void borrarProducto(Integer id) throws ProductoInexistenteException {
		try {
			productoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new ProductoInexistenteException("Producto con id '" + id + "' inexistente.");
		}
	}

	public Producto getProducto(String id) throws ProductoInexistenteException {
		Optional<Producto> productoFounded = productoRepository.findById(Integer.valueOf(id));
		if (productoFounded.isPresent()) {
			return productoFounded.get();
		}
		throw new ProductoInexistenteException("Producto con id '" + id + "' inexistente.");
	}

}
