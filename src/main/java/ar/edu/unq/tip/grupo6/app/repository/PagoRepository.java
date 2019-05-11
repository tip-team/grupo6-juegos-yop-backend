package ar.edu.unq.tip.grupo6.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unq.tip.grupo6.app.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, String> {

}