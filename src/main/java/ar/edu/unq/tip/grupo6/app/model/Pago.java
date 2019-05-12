package ar.edu.unq.tip.grupo6.app.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "pagos")
public class Pago {
	
	@Id String idCompra;
	String nombreProducto;
	Float monto;
	Float montoRecibido;
	String estadoDePago;
	String fecha;
	String email;
	
	public Pago(String idCompra, String nombreProducto, Float monto, Float montoRecibido, String estadoDePago, String email) {
		this.idCompra = idCompra;
		this.nombreProducto = nombreProducto;
		this.monto = monto;
		this.montoRecibido = montoRecibido;
		this.estadoDePago = estadoDePago;
		ZonedDateTime fecha = ZonedDateTime.now();
		ZonedDateTime fechaArgentina = fecha.withZoneSameInstant(ZoneId.of("America/Argentina/Buenos_Aires"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.fecha = fechaArgentina.format(formatter);
		this.email = email;
	}
	
}
