package vacinahub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void deveCadastrarUsuarioComSucesso() {
        AuthService service = new AuthService();

        String resultado = service.cadastrar("João", "joao@email.com", "123456");

        assertEquals("Cadastro realizado com sucesso", resultado);
        assertEquals(1, service.totalUsuarios());
    }

    @Test
    void naoDeveCadastrarSenhaCurta() {
        AuthService service = new AuthService();

        String resultado = service.cadastrar("João", "joao@email.com", "123");

        assertEquals("Senha deve ter no mínimo 6 caracteres", resultado);
        assertEquals(0, service.totalUsuarios());
    }

    @Test
    void deveFazerLoginComSucesso() {
        AuthService service = new AuthService();

        service.cadastrar("João", "joao@email.com", "123456");
        String resultado = service.login("joao@email.com", "123456");

        assertEquals("Login realizado com sucesso", resultado);
    }

    @Test
    void naoDeveFazerLoginComSenhaErrada() {
        AuthService service = new AuthService();

        service.cadastrar("João", "joao@email.com", "123456");
        String resultado = service.login("joao@email.com", "000000");

        assertEquals("Email ou senha inválidos", resultado);
    }
}