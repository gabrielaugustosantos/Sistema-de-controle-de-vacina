package vacinahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vacinahub.domain.Usuario;
import vacinahub.infra.UsuarioRepository;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String cadastrar(String nome, String email, String senha) {
        if (senha == null || senha.length() < 6) {
            return "Senha deve ter no mínimo 6 caracteres";
        }

        // Busca direto na tabela se o e-mail já existe
        if (usuarioRepository.findByEmail(email) != null) {
            return "Email já cadastrado";
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);
        usuarioRepository.save(novoUsuario); // Salva o registro no banco H2
        return "Cadastro realizado com sucesso";
    }

    public String login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "Login realizado com sucesso";
        }

        return "Email ou senha inválidos";
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public long totalUsuarios() {
        return usuarioRepository.count(); // Retorna o total de registros salvos na tabela
    }
}