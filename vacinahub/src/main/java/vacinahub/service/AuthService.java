package vacinahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vacinahub.domain.Usuario;
import vacinahub.infra.UsuarioRepository;

/**
 * Serviço responsável pela lógica de autenticação, controle de acessos
 * e validações de contas de usuários.
 */
@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Valida os dados e realiza o cadastro de um novo usuário titular.
     * @return String contendo a mensagem de feedback sobre a operação.
     */
    public String cadastrar(String nome, String email, String senha) {
        if (senha == null || senha.length() < 6) {
            return "Senha deve ter no mínimo 6 caracteres";
        }

        if (usuarioRepository.findByEmail(email) != null) {
            return "Email já cadastrado";
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);
        usuarioRepository.save(novoUsuario);
        return "Cadastro realizado com sucesso";
    }

    /**
     * Valida as credenciais para autorizar o acesso ao sistema.
     * @return String indicando o sucesso ou falha na autenticação.
     */
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
        return usuarioRepository.count();
    }
}