package ar.edu.unq.tip.grupo6.app.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unq.tip.grupo6.app.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	@Transactional
	List<Producto> findAllByOrderByPrioridadAsc();

}