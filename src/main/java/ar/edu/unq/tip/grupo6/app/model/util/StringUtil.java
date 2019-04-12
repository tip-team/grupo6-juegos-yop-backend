package ar.edu.unq.tip.grupo6.app.model.util;

public class StringUtil {

	public static String encodeText(String text) {
		return text.chars().mapToObj(c -> changeCharEncoding((char) c)).reduce("", (x,y) -> x + y);
	}
	
	private static String changeCharEncoding(char character) {
		if (character == 'á') {
			return "&aacute;";
		}
		
		if (character == 'é') {
			return "&eacute;";
		}
		
		if (character == 'í') {
			return "&iacute;";
		}
		
		if (character == 'ó') {
			return "&oacute;";
		}
		
		if (character == 'ú') {
			return "&uacute;";
		}
		
		if (character == 'ñ') {
			return "&ntilde;";
		}
		
		if (character == 'Á') {
			return "&Aacute;";
		}
		
		if (character == 'É') {
			return "&Eacute;";
		}
		
		if (character == 'Í') {
			return "&Iacute;";
		}
		
		if (character == 'Ó') {
			return "&Oacute;";
		}
		
		if (character == 'Ú') {
			return "&Úacute;";
		}
		
		if (character == 'Ñ') {
			return "&Ntilde;";
		}
		
		return String.valueOf(character);
	}
	
}
