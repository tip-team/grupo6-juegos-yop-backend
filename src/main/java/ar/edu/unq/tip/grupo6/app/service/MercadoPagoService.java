package ar.edu.unq.tip.grupo6.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.MerchantOrder;
import com.mercadopago.resources.Payment;

import ar.edu.unq.tip.grupo6.app.model.MercadoPago;
import ar.edu.unq.tip.grupo6.app.model.Pago;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.repository.PagoRepository;
import ar.edu.unq.tip.grupo6.app.repository.ProductoRepository;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;

@Component
public class MercadoPagoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private PagoRepository pagoRepository;

	public String getPaymentUrl(Integer productoId) throws MPException, ProductoInexistenteException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ProductoInexistenteException("El producto con id '" + productoId + "' es inexistente."));
		return MercadoPago.getPaymentUrl(producto);
	}

	public void savePayment(String id) throws MPException {
		Payment payment = Payment.findById(id);
		Pago pago = new Pago(id, payment.getDescription(), 
				payment.getTransactionDetails().getTotalPaidAmount(), 
				payment.getTransactionDetails().getNetReceivedAmount(),
				payment.getStatus().name());
		pagoRepository.save(pago);
	}
	
	public List<Pago> getPagos() {
		return pagoRepository.findAll();
	}

}
