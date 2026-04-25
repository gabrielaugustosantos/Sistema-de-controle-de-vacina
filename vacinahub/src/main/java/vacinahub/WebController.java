package vacinahub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}