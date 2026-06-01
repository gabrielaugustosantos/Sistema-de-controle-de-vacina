package vacinahub.infra;

import vacinahub.domain.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
}