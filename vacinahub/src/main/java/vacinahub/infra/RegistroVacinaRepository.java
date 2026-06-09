package vacinahub.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;

@Repository
public interface RegistroVacinaRepository extends JpaRepository<RegistroVacina, Long> {
    List<RegistroVacina> findByUsuario(Usuario usuario);
}