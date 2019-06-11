package ar.edu.unq.tip.grupo6.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "serial")
	@Generated(GenerationTime.INSERT)
	private Integer prioridad;
	
	@NotNull(message = "El nombre debe estar definido.")
	@Size(min = 1, message = "El nombre debe estar definido.")
	private String nombre;
	
	@NotNull(message="El precio debe estar definido.")
	private Float precio;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@NotNull(message = "La imágen debe estar definida.")
    @Size(min = 1, message = "La imágen debe estar definida.")
	private String imagen;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@NotNull(message = "La imágen debe estar definida.")
	@Size(min = 1, message = "La imágen debe estar definida.")
	private String imagenDesc;

	@NotNull(message = "Se debe definir si el producto esta habilitado.")
	private Boolean habilitado;
	
}
