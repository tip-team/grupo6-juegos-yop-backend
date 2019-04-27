package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import ar.edu.unq.tip.grupo6.app.service.MercadoPagoService;
import ar.edu.unq.tip.grupo6.app.service.exception.ProductoInexistenteException;
import ar.edu.unq.tip.grupo6.app.util.ConfigurationLoader;
import ar.edu.unq.tip.grupo6.app.webservice.annotation.BadRequestId;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;
import ar.edu.unq.tip.grupo6.app.webservice.exception.NotFoundException;

@Component
@Path("/mp")
public class MercadoPagoRest extends Rest {
	
	@Autowired
	private MercadoPagoService mercadoPagoService;
	
	public MercadoPagoRest() throws MPConfException {
		super();
		// Se configura la SDK para poder interactuar con Mercado Pago.
		MercadoPago.SDK.setAccessToken(ConfigurationLoader.MERCADO_PAGO_ACCESS_TOKEN);
	}
	
	private String getUrlPago(Integer id) throws MPException, NotFoundException {
		String res = null;
		try{
			res = mercadoPagoService.getPaymentUrl(id);
		}
		catch(ProductoInexistenteException productoInexistente) {
			notFound(productoInexistente.getMessage());
		}
		return res;
	}
	
	@GET
	@BadRequestId(message = "obtener la url del pago")
	@Path("/obtenerUrlPago/{id}")
	@Produces(APPLICATION_JSON)
	public Response getUrlPagoConChequeo(@PathParam("id") String id) throws MPException, NotFoundException, BadRequestException {
		String urlPago = getUrlPago(Integer.valueOf(id));
		return ok(json -> json.add("urlPago", urlPago));
	}
	
	@POST
	@Path("/notifications")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response getNotification(@QueryParam("id") String id, @QueryParam("type") String type) throws MPException {
		//Payment payment = Payment.findById(id);
		//MerchantOrder merchantOrder = MerchantOrder.findById(payment.getId());
		Logger logger = LoggerFactory.getLogger(MercadoPagoRest.class);
		logger.info("Recibi una notificacion");
		return ok();
	}

}
