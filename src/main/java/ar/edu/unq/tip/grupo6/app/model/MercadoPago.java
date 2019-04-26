package ar.edu.unq.tip.grupo6.app.model;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import ar.edu.unq.tip.grupo6.app.model.util.StringUtil;

public class MercadoPago {
	
	public static String getPaymentUrl(Producto producto) throws MPException {
		Item item = new Item()
				.setId(String.valueOf(producto.getId()))
				.setTitle(StringUtil.encodeText(producto.getNombre()))
				.setQuantity(1)
				.setUnitPrice(producto.getPrecio());

		return (new Preference())
					.setPayer(new Payer())
					.appendItem(item)
					.setBackUrls((new BackUrls()).setSuccess("https://tip-juegos-yop.herokuapp.com"))
					.setAutoReturn(AutoReturn.approved)
					.setNotificationUrl("https://test-tip-backend.herokuapp.com/api/mp/notifications")
					.save()
					.getInitPoint();
	}

}
