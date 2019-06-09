package ar.edu.unq.tip.grupo6.app.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.MerchantOrder;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Payment.Status;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.payment.TransactionDetails;
import com.mercadopago.resources.datastructures.preference.Payer;
import ar.edu.unq.tip.grupo6.app.model.IntencionDePago;
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

	public String getPaymentUrl(Integer productoId, String email, String telefono, String nombre) throws MPException, ProductoInexistenteException, UnsupportedEncodingException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ProductoInexistenteException("El producto con id '" + productoId + "' es inexistente."));
		IntencionDePago intencionDePago = new IntencionDePago(productoId.toString(), email, telefono, nombre);
		Validator.validate(intencionDePago);
		return MercadoPago.getPaymentUrl(producto.getNombre(), producto.getPrecio(), intencionDePago);
	}

	public void savePayment(String id) throws MPException {
		Payment payment = Payment.findById(id);
		MerchantOrder merchantOrder = MerchantOrder.findById(payment.getOrder().getId().toString());
		Preference preference = Preference.findById(merchantOrder.getPreferenceId());
		Payer payer = preference.getPayer();
		TransactionDetails transactionDetails = payment.getTransactionDetails();
		Pago pago = new Pago(id, payment.getDescription(), 
				transactionDetails.getTotalPaidAmount(), 
				transactionDetails.getNetReceivedAmount(),
				getEstado(payment.getStatus()),
				payer.getEmail(),
				payer.getPhone().getNumber(),
				payer.getName());
		pagoRepository.save(pago);
	}
	
	public List<Pago> getPagos() {
		return pagoRepository.findByOrderByIdCompraDesc();
	}
	
	private String getEstado(Status status) {
		switch(status) {
			case approved: return "APROBADO";
			case in_process: return "EN PROCESO";
			case pending: return "PENDIENTE";
			case cancelled: return "CANCELADO";
			case authorized: return "AUTORIZADO";
			case charged_back: return "REVERTIDO";
			case in_mediation: return "EN MEDIACIÓN";
			case refunded: return "REINTEGRADO";
			case rejected: return "RECHAZADO";
			default: return status.name();
		}
	}

}
