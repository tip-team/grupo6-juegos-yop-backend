package ar.edu.unq.tip.grupo6.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mercadopago.exceptions.MPException;
import ar.edu.unq.tip.grupo6.app.model.MercadoPago;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.repository.ProductoRepository;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;

@Component
public class MercadoPagoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public String getPaymentUrl(Integer productoId) throws MPException, ProductoInexistenteException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new ProductoInexistenteException("El producto con id '" + productoId + "' es inexistente."));
		return MercadoPago.getPaymentUrl(producto);
	}

}
