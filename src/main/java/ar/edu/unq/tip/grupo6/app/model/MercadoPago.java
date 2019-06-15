package ar.edu.unq.tip.grupo6.app.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import static java.nio.charset.StandardCharsets.UTF_8;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import static com.mercadopago.resources.Preference.AutoReturn.approved;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import ar.edu.unq.tip.grupo6.app.model.util.StringUtil;
import static ar.edu.unq.tip.grupo6.app.util.ConfigurationLoader.*;

public class MercadoPago {
	
	public static String getPaymentUrl(String nombreProducto, Float precioProducto, IntencionDePago intencionDePago) throws MPException, UnsupportedEncodingException {
		Preference preference = new Preference()
				.setPayer(createPayer(intencionDePago))
				.appendItem(createItem(intencionDePago.getId(), nombreProducto, precioProducto))
				.setBackUrls((new BackUrls()).setSuccess(JUEGOS_YOP_FRONTEND_HOME))
				.setAutoReturn(approved)
				.setNotificationUrl(JUEGOS_YOP_NOTIFICATION_ENDPOINT)
				.save();
		return preference.getInitPoint();
	}
	
	private static Payer createPayer(IntencionDePago intencionDePago) throws UnsupportedEncodingException {
		return new Payer()
				.setEmail(intencionDePago.getEmail())
				.setPhone((new Phone()).setNumber(URLDecoder.decode(intencionDePago.getTelefono(), UTF_8.name())))
				.setName(intencionDePago.getNombre());
	}
	
	private static Item createItem(String id, String nombreProducto, Float precioProducto) {
		return new Item()
				.setId(id)
				.setTitle(StringUtil.encodeText(nombreProducto))
				.setPictureUrl("https://tip-juegos-yop-backend.herokuapp.com/api/productos/imagen/" + id)
				.setQuantity(1)
				.setUnitPrice(precioProducto);
	}

}
