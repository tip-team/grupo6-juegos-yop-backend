package ar.edu.unq.tip.grupo6.app.service.exception;

public class ProductoInexistenteException extends Exception {

	private static final long serialVersionUID = 6134788968935176195L;

	public ProductoInexistenteException(String id) {
		super("Producto con id '" + id + "' inexistente.");
	}
	
}
