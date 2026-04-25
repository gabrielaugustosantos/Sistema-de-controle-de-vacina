package vacinahub;

import io.cucumber.java.pt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthSteps {

    private AuthService service;
    private String resultado;

    @Dado("que o sistema de autenticação está iniciado")
    public void iniciarSistema() {
        service = new AuthService();
    }

    @Quando("cadastro o usuário {string} com email {string} e senha {string}")
    public void cadastroUsuario(String nome, String email, String senha) {
        resultado = service.cadastrar(nome, email, senha);
    }

    @E("existe um usuário {string} com email {string} e senha {string}")
    public void existeUsuario(String nome, String email, String senha) {
        service.cadastrar(nome, email, senha);
    }

    @Quando("faço login com email {string} e senha {string}")
    public void facoLogin(String email, String senha) {
        resultado = service.login(email, senha);
    }

    @Então("o resultado deve ser {string}")
    public void verificarResultado(String esperado) {
        assertEquals(esperado, resultado);
    }
}