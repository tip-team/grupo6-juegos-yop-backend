package ar.edu.unq.tip.grupo6.app.service.dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import ar.edu.unq.tip.grupo6.app.model.Pago;
import lombok.Getter;

@Getter
public class PagoDTO {

	String idCompra;
	String nombreProducto;
	Float monto;
	Float montoRecibido;
	String estadoDePago;
	String fecha;
	String email;
	String telefono;
	String nombre;

	public PagoDTO(Pago pago) {
		this.idCompra = pago.getIdCompra();
		this.nombreProducto = pago.getNombreProducto();
		this.monto = pago.getMonto();
		this.montoRecibido = pago.getMontoRecibido();
		this.estadoDePago = pago.getEstadoDePago();
		ZonedDateTime fechaArgentina = pago.getFecha().withZoneSameInstant(ZoneId.of("America/Argentina/Buenos_Aires"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fecha = fechaArgentina.format(formatter);
		this.email = pago.getEmail();
		this.telefono = pago.getTelefono();
		this.nombre = pago.getNombre();
	}

}
