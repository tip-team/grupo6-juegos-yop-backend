package ar.edu.unq.tip.grupo6.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import ar.edu.unq.tip.grupo6.app.model.MercadoPago;
import ar.edu.unq.tip.grupo6.app.model.Producto;
import ar.edu.unq.tip.grupo6.app.repository.ProductoRepository;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;

@Component
public class MercadoPagoService {

	@Autowired
	private ProductoRepository productoRepository;

	public String getPaymentUrl(Integer productoId) throws MPException, ProductoInexistenteException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ProductoInexistenteException("El producto con id '" + productoId + "' es inexistente."));
		return MercadoPago.getPaymentUrl(producto);
	}

	public void savePayment(String id) throws MPException {
		Payment payment = Payment.findById(id);
		Logger logger = LoggerFactory.getLogger(MercadoPagoService.class);
		logger.info("PAYMENT ID: " + id);
		logger.info("PAYMENT DESCRIPTION: " + payment.getDescription());
		logger.info("PAYMENT STATUS: " + payment.getStatus());
		logger.info("PAYMENT STATUS DETAIL: " + payment.getStatusDetail());
		logger.info("Valor cobrado: " + payment.getTransactionDetails().getTotalPaidAmount());
		logger.info("Valor real con comision cobrada: " + payment.getTransactionDetails().getNetReceivedAmount());
	}

}
