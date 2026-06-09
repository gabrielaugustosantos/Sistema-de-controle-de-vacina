package vacinahub.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vacinahub.domain.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}