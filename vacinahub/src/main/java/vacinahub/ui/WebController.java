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
    // Rota que recebe os dados do HTML de login
    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, Model model) {
        
        // Pede para o serviço validar
        String resultado = authService.login(email, senha);

        if (resultado.equals("Login realizado com sucesso")) {
            // Sucesso! Colocamos o nome na mochila do Model para o Thymeleaf exibir no h2 da tela.
            // (Para este MVP, deixamos fixo. No futuro, puxaremos o nome real do banco).
            model.addAttribute("nomeUsuario", "Maria"); 
            
            // Retorna o nome do novo arquivo HTML (dashboard.html)
            return "dashboard"; 
        } else {
            // Erro! Devolve para a tela inicial com o aviso em vermelho
            model.addAttribute("mensagemErro", resultado);
            return "index";
        }
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