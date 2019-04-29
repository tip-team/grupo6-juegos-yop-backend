package ar.edu.unq.tip.grupo6.app.util;

public class ConfigurationLoader {
	
	public final static String MERCADO_PAGO_ACCESS_TOKEN = ConfigurationLoader.getAccessToken();
	
	private static String getAccessToken() {
		return System.getenv("JUEGOS_YOP_MERCADOPAGO_ACCESS_TOKEN");
	}

}
