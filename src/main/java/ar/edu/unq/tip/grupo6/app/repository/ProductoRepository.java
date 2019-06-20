package ar.edu.unq.tip.grupo6.app.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.unq.tip.grupo6.app.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	@Transactional
	List<Producto> findAllByOrderByPrioridadAsc();
	
	@Transactional
	@Query("SELECT producto.imagen FROM Producto producto WHERE producto.id = :id")
	String getImagen(@Param("id") Integer id);
	
	@Transactional
	@Query("SELECT producto.imagenDesc FROM Producto producto WHERE producto.id = :id")
	String getImagenDesc(@Param("id") Integer id);
	
	@Modifying
	@Query("UPDATE Producto producto SET producto.prioridad = :prioridad WHERE producto.id = :id")
	void updatePrioridad(@Param("id") Integer id, @Param("prioridad") Integer prioridad);
	
	@Transactional
	@Modifying
	@Query("UPDATE Producto producto SET producto.nombre = :nombre, "
			+ "producto.precio = :precio, "
			+ "producto.imagen = :imagen, "
			+ "producto.imagenDesc = :imagenDesc, "
			+ "producto.habilitado = :habilitado WHERE producto.id = :id")
	void updateProducto(@Param("id") Integer id, @Param("nombre") String nombre, 
			@Param("precio") Float precio, @Param("imagen") String imagen, 
			@Param("imagenDesc") String imagenDesc, @Param("habilitado") Boolean habilitado);

}