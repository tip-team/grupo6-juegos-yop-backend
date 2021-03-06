package ar.edu.unq.tip.grupo6.app.model;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mercadopago.resources.datastructures.preference.Payer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "pagos")
public class Pago {
	
	@Id 
	private String idCompra;
	
	private String nombreProducto;
	
	private Float monto;
	
	private Float montoRecibido;
	
	private String estadoDePago;
	
	private ZonedDateTime fecha;
	
	private String email;
	
	private String telefono;
	
	private String nombre;
	
	public Pago(String idCompra, String nombreProducto, Float monto, Float montoRecibido, String estadoDePago, Payer payer) {
		this.idCompra = idCompra;
		this.nombreProducto = nombreProducto;
		this.monto = monto;
		this.montoRecibido = montoRecibido;
		this.estadoDePago = estadoDePago;
		this.fecha = ZonedDateTime.now();
		this.email = payer.getEmail();
		this.telefono = payer.getPhone().getNumber();
		this.nombre = payer.getName();
	}
	
}
