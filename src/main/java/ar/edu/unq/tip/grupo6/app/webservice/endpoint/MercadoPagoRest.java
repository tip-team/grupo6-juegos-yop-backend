package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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
import ar.edu.unq.tip.grupo6.app.webservice.util.RestUtils;
import lombok.SneakyThrows;

@Component
@Path("/mp")
public class MercadoPagoRest extends Rest {
	
	@Autowired
	private MercadoPagoService mercadoPagoService;
	
	public MercadoPagoRest() throws MPConfException {
		super();
		MercadoPago.SDK.setAccessToken(ConfigurationLoader.MERCADO_PAGO_ACCESS_TOKEN);
	}
	
	@GET
	@BadRequestId(message = "obtener la url del pago")
	@Path("/obtenerUrlPago")
	@Produces(APPLICATION_JSON)
	public Response getUrlPagoConChequeo(@QueryParam("id") String id, @QueryParam("email") String email, @QueryParam("telefono") String telefono, @QueryParam("nombre") String nombre) throws MPException, NotFoundException, BadRequestException, NumberFormatException, UnsupportedEncodingException, ProductoInexistenteException {
		String urlPago = mercadoPagoService.getPaymentUrl(id, email, telefono, nombre);
		return ok(json -> json.add("urlPago", urlPago));
	}
	
	@POST
	@Path("/notifications")
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response getNotification(@QueryParam("data.id") String dataId, @QueryParam("type") String type, @QueryParam("id") String id) throws MPException {
		Optional.ofNullable(type).ifPresent(paymentType -> {
			String idString = Optional.ofNullable(dataId).orElse(id);
			try {
				mercadoPagoService.savePayment(idString);
			} catch (MPException e) {
				lanzarExcepcionMercadoPago(new BadRequestException("El id de compra de Mercado Pago es inexistente.", logger));
			}
		});
		return ok();
	}
	
	@GET
	@Path("/pagos")
	@Produces(APPLICATION_JSON)
	public Response getPagos() {
		return RestUtils.ok(mercadoPagoService.getPagos());
	}
	
	@SneakyThrows
	private void lanzarExcepcionMercadoPago(BadRequestException exception) {
		throw exception;
	}

}
