package ar.edu.unq.tip.grupo6.app.webservice.endpoint;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import java.util.function.Function;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import ar.edu.unq.tip.grupo6.app.util.ConfigurationLoader;
import ar.edu.unq.tip.grupo6.app.webservice.exception.BadRequestException;

@Component
@Path("/obtenerUrlPago")
public class MercadoPagoRest extends Rest {
	
	public MercadoPagoRest() throws MPConfException {
		super();
		// Se configura la SDK para poder interactuar con Mercado Pago.
		MercadoPago.SDK.setAccessToken(ConfigurationLoader.MERCADO_PAGO_ACCESS_TOKEN);
	}
	
	@GET
	@Produces(APPLICATION_JSON)
	public Response getUrlPago(@QueryParam("id") String id, @QueryParam("titulo") String titulo, @DefaultValue("1") @QueryParam("cantidad") String cantidad, @QueryParam("precio") String precio) throws MPException, BadRequestException {
		String exceptionMessage = "Para obtener la url del pago ";
		Function<String,String> nullMessage = tipo -> exceptionMessage + "debe ingresar " + tipo + ".";
		nullMessage.apply("el id");
		
		// Lanza una BadRequestException en caso que el id sea null, vacío o no sea un número entero.
		throwIntegerException(id, nullMessage.apply("el id"), exceptionMessage + "el id debe ser un número entero.");
		
		// Lanza una BadRequestException en caso que el título sea null o vacío.
		throwNullOrEmptyException(titulo, nullMessage.apply("el título"));
		
		// Lanza una BadRequestException en caso que la cantidad sea null, vacío o no sea un número entero.
		Integer cantidadNumber = throwIntegerException(cantidad, nullMessage.apply("la cantidad"), exceptionMessage + "la cantidad debe ser un número entero.");
		
		// Lanza una BadRequestException en caso que el precio sea null, vacío o no sea un float.
		Float precioNumber = throwFloatException(precio, nullMessage.apply("el precio"), exceptionMessage + "el precio debe ser un número.");
		
		Item item = new Item()
						.setId(id)
						.setTitle(titulo)
						.setQuantity(cantidadNumber)
						.setUnitPrice(precioNumber);
		
		Preference preference = new Preference()
				.setPayer(new Payer())
				.appendItem(item)
				.setBackUrls((new BackUrls()).setSuccess("https://tip-juegos-yop.herokuapp.com"))
				.setAutoReturn(AutoReturn.approved)
				.save();

		return ok(json -> json.add("urlPago", preference.getInitPoint()));
	}
	
	private <T> T getNumber(String number, String message, String numberMessage, Function<String,T> funcion) throws BadRequestException {
		throwNullOrEmptyException(number, message);
		try {
			return funcion.apply(number);
		} catch (NumberFormatException numberException) {
			badRequest(numberMessage);
		}
		return null;
	}
	
	private Integer throwIntegerException(String integer, String message, String numberMessage) throws BadRequestException {
		return getNumber(integer, message, numberMessage, number -> Integer.valueOf(number));
	}
	
	private Float throwFloatException(String floatString, String message, String numberMessage) throws BadRequestException {
		return getNumber(floatString, message, numberMessage, number -> Float.valueOf(number));
	}

}
