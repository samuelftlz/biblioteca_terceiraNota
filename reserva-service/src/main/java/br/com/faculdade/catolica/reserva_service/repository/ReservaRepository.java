package br.com.faculdade.catolica.reserva_service.repository;

import br.com.faculdade.catolica.reserva_service.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}