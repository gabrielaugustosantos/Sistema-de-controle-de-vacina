package vacinahub;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private List<Usuario> usuarios = new ArrayList<>();

    public String cadastrar(String nome, String email, String senha) {
        if (senha.length() < 6) {
            return "Senha deve ter no mínimo 6 caracteres";
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return "Email já cadastrado";
            }
        }

        usuarios.add(new Usuario(nome, email, senha));
        return "Cadastro realizado com sucesso";
    }

    public String login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return "Login realizado com sucesso";
            }
        }

        return "Email ou senha inválidos";
    }

    public int totalUsuarios() {
        return usuarios.size();
    }
}