package vacinahub.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;

/**
 * Repositório responsável pelas operações de persistência do histórico de vacinas.
 */
@Repository
public interface RegistroVacinaRepository extends JpaRepository<RegistroVacina, Long> {
    
    /**
     * Retorna o histórico completo de vacinas vinculadas a um usuário específico.
     * @param usuario Instância do usuário titular.
     * @return List de RegistroVacina contendo todas as doses do usuário.
     */
    List<RegistroVacina> findByUsuario(Usuario usuario);
}