package ar.edu.unq.tip.grupo6.app.util;

import static java.lang.System.getenv;

public class ConfigurationLoader {
	
	public final static String MERCADO_PAGO_ACCESS_TOKEN = ConfigurationLoader.getAccessToken();
	public final static String JUEGOS_YOP_EMAIL = ConfigurationLoader.getJuegosYopEmail();
	public final static String JUEGOS_YOP_EMAIL_API_KEY = ConfigurationLoader.getJuegosYopEmailApiKey();
	public final static String JUEGOS_YOP_NOTIFICATION_ENDPOINT = ConfigurationLoader.getJuegosYopNotificationEndpoint();
	public final static String JUEGOS_YOP_FRONTEND_HOME = ConfigurationLoader.getJuegosYopFrontendHome();
	public final static String JUEGOS_YOP_SECRET_TOKEN = ConfigurationLoader.getJuegosYopSecretToken();
	
	private static String getAccessToken() {
		return getenv("JUEGOS_YOP_MERCADOPAGO_ACCESS_TOKEN");
	}
	
	private static String getJuegosYopNotificationEndpoint() {
		return getenv("JUEGOS_YOP_NOTIFICATION_ENDPOINT");
	}

	private static String getJuegosYopEmail() {
		return getenv("JUEGOS_YOP_EMAIL");
	}
	
	private static String getJuegosYopEmailApiKey() {
		return getenv("JUEGOS_YOP_EMAIL_API_KEY");
	}
	
	private static String getJuegosYopFrontendHome() {
		return getenv("JUEGOS_YOP_FRONTEND_HOME");
	}
	
	private static String getJuegosYopSecretToken() {
		return getenv("JUEGOS_YOP_SECRET_TOKEN");
	}

}
