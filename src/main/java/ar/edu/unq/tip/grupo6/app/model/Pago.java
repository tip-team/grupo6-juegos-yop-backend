package ar.edu.unq.tip.grupo6.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
	
}
