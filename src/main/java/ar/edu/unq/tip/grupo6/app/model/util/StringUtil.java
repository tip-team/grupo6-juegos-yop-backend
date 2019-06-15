package ar.edu.unq.tip.grupo6.app.model.util;

public class StringUtil {

	public static String encodeText(String text) {
		return text.chars().mapToObj(c -> changeCharEncoding((char) c)).reduce("", (x,y) -> x + y);
	}
	
	private static String changeCharEncoding(char character) {
		switch (character) {
		 case 'á': return "&aacute;";
		 case 'Á': return "&Aacute;";
		 case 'é': return "&eacute;";
		 case 'É': return "&Eacute;";
		 case 'í': return "&iacute;";
		 case 'Í': return "&Iacute;";
		 case 'ó': return "&oacute;";
		 case 'Ó': return "&Oacute;";
		 case 'ú': return "&uacute;";
		 case 'Ú': return "&Úacute;";
		 case 'ñ': return "&ntilde;";
		 case 'Ñ': return "&Ntilde;";
		 default: return String.valueOf(character);
		}
	}
	
}
