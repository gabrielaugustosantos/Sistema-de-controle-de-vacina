package vacinahub.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vacinahub.domain.Usuario;

/**
 * Repositório responsável pelas operações de persistência da entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Busca um usuário no banco de dados através do e-mail.
     * @param email E-mail cadastrado.
     * @return Usuario encontrado ou null se não existir.
     */
    Usuario findByEmail(String email); 
}