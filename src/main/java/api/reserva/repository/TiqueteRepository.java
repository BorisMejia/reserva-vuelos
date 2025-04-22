package api.reserva.repository;

import api.reserva.entity.Tiquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiqueteRepository extends JpaRepository<Tiquete, Long> {
}
