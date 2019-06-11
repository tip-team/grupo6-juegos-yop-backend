package ar.edu.unq.tip.grupo6.app.service;

import java.util.List;
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
		return productoRepository.findAllByOrderByPrioridadAsc();
	}

	public Integer createProducto(@Valid Producto producto) {
		return productoRepository.save(producto).getId();
	}

	public void updateProducto(@Valid Producto producto) {
		Validator.validate(producto);
		productoRepository.save(producto);
	}

	public void borrarProducto(String id) throws ProductoInexistenteException {
		try {
			productoRepository.deleteById(Integer.valueOf(id));
		} catch (EmptyResultDataAccessException exception) {
			throw new ProductoInexistenteException(id);
		}
	}
	
	public Producto getProducto(String id) throws ProductoInexistenteException {
		return productoRepository.findById(Integer.valueOf(id)).orElseThrow(
				() -> new ProductoInexistenteException(id));
	}

	public String getProductoDesc(String id) throws ProductoInexistenteException {
		return getProducto(id).getImagenDesc();
	}
	
}
