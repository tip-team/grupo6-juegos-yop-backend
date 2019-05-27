package ar.edu.unq.tip.grupo6.app.util;

public class ConfigurationLoader {
	
	public final static String MERCADO_PAGO_ACCESS_TOKEN = ConfigurationLoader.getAccessToken();
	public final static String JUEGOS_YOP_EMAIL = ConfigurationLoader.getJuegosYopEmail();
	public final static String JUEGOS_YOP_EMAIL_API_KEY = ConfigurationLoader.getJuegosYopEmailApiKey();
	
	private static String getAccessToken() {
		return System.getenv("JUEGOS_YOP_MERCADOPAGO_ACCESS_TOKEN");
	}
	
	private static String getJuegosYopEmail() {
		return System.getenv("JUEGOS_YOP_EMAIL");
	}
	
	private static String getJuegosYopEmailApiKey() {
		return System.getenv("JUEGOS_YOP_EMAIL_API_KEY");
	}

}
