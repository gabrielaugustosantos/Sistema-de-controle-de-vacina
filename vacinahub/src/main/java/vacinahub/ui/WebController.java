package vacinahub.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vacinahub.service.AuthService;
import vacinahub.domain.Usuario;

@Controller
public class WebController {

    @Autowired // Puxa o nosso AuthService automaticamente
    private AuthService authService;

    @GetMapping("/")
    public String paginaInicial() {
        return "index";
    }

    // Nova rota que recebe os dados do HTML
    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, Model model) {
        
        // Pede para o serviço validar
        String resultado = authService.login(email, senha);

        if (resultado.equals("Login realizado com sucesso")) {
            // Deu certo! Manda uma mensagem verde pra tela
            model.addAttribute("mensagemSucesso", "Bem-vinda, Maria! Redirecionando para a caderneta...");
            return "index"; 
        } else {
            // Deu erro! Manda a mensagem vermelha pra tela
            model.addAttribute("mensagemErro", resultado);
            return "index";
        }
    }
    // Rota para mostrar a tela de cadastro
    @GetMapping("/cadastro")
    public String paginaCadastro() {
        return "cadastro";
    }

    // Rota que recebe os dados do formulário HTML
    @PostMapping("/cadastrar")
    public String fazerCadastro(@RequestParam String nome, @RequestParam String email, @RequestParam String senha, Model model) {
        
        // Pede para o serviço processar a regra de negócio
        String resultado = authService.cadastrar(nome, email, senha);

        if (resultado.equals("Cadastro realizado com sucesso")) {
            // Se deu certo, manda uma mensagem verde e joga o usuário de volta para a tela de login
            model.addAttribute("mensagemSucesso", "Conta criada com sucesso! Faça seu login.");
            return "index"; 
        } else {
            // Se deu erro (senha curta, email já usado), devolve o erro e mantém na tela de cadastro
            model.addAttribute("mensagemErro", resultado);
            return "cadastro";
        }
    }
}