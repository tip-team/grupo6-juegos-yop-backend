package ar.edu.unq.tip.grupo6.app.model;

import java.time.ZonedDateTime;
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
	ZonedDateTime fecha;
	String email;
	String telefono;
	String nombre;
	
	public Pago(String idCompra, String nombreProducto, Float monto, Float montoRecibido, String estadoDePago, String email, String telefono, String nombre) {
		this.idCompra = idCompra;
		this.nombreProducto = nombreProducto;
		this.monto = monto;
		this.montoRecibido = montoRecibido;
		this.estadoDePago = estadoDePago;
		this.fecha = ZonedDateTime.now();
		this.email = email;
		this.telefono = telefono;
		this.nombre = nombre;
	}
	
}
