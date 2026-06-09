package vacinahub.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vacinahub.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // O Spring Data JPA traduz isto automaticamente para: 
    // SELECT * FROM tb_usuario WHERE email = ?
    Usuario findByEmail(String email); 
}