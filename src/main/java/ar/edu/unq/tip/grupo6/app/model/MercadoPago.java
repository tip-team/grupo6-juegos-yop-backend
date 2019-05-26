package ar.edu.unq.tip.grupo6.app.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import ar.edu.unq.tip.grupo6.app.model.util.StringUtil;

public class MercadoPago {
	
	public static String getPaymentUrl(String nombreProducto, Float precioProducto, IntencionDePago intencionDePago) throws MPException, UnsupportedEncodingException {
		Item item = new Item()
				.setId(String.valueOf(intencionDePago.getId()))
				.setTitle(StringUtil.encodeText(nombreProducto))
				.setQuantity(1)
				.setUnitPrice(precioProducto);
		Phone phone = new Phone();
		String phoneNumber = URLDecoder.decode(intencionDePago.getTelefono(), StandardCharsets.UTF_8.name());
		phone.setNumber(phoneNumber);
		Preference preference = (new Preference())
				.setPayer((new Payer()).setEmail(intencionDePago.getEmail()).setPhone(phone).setName(intencionDePago.getNombre()))
				.appendItem(item)
				.setBackUrls((new BackUrls()).setSuccess("https://tip-juegos-yop.herokuapp.com"))
				.setAutoReturn(AutoReturn.approved)
				.setNotificationUrl("https://tip-juegos-yop-backend.herokuapp.com/api/mp/notifications")
				.save();
		return preference.getInitPoint();
	}

}
