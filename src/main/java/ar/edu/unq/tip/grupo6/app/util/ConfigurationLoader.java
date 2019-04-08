package ar.edu.unq.tip.grupo6.app.util;

import java.util.Optional;
import java.util.Properties;

public class ConfigurationLoader {
	
	private final static Properties PROPERTIES = ConfigurationLoader.getProperties();
	
	private final static String CONFIGURATION_FILE = "configuration.properties";
	
	public final static String MERCADO_PAGO_ACCESS_TOKEN = ConfigurationLoader.getAccessToken();
			
	private static Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(ConfigurationLoader.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILE));
		} catch (Exception e) {
			throw new RuntimeException("No se puedo cargar las properties de Mercado Pago.");
		}
		return properties;
	}
	
	private static String getAccessToken() {
		String accessToken = "ACCESS_TOKEN";
		return Optional.ofNullable(PROPERTIES.getProperty(accessToken)).orElseThrow(() -> new RuntimeException("No se encontro la property '" + accessToken + "' en el archivo '" + CONFIGURATION_FILE + "'."));
	}

}
