package vacinahub.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vacinahub.domain.*;
import vacinahub.infra.*;
import vacinahub.service.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired private AuthService authService;
    @Autowired private VacinaService vacinaService;
    @Autowired private VacinaRepository vacinaRepository;
    @Autowired private RegistroVacinaRepository registroVacinaRepository;

    @GetMapping("/") public String index() { return "index"; }
    @GetMapping("/cadastro") public String cadastro() { return "cadastro"; }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/";

        List<RegistroVacina> registros = registroVacinaRepository.findByUsuario(usuario);
        model.addAttribute("registros", registros);
        model.addAttribute("nomeUsuario", usuario.getNome());
        return "dashboard";
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        String resultado = authService.login(email, senha);
        if (resultado.equals("Login realizado com sucesso")) {
            session.setAttribute("usuarioLogado", authService.buscarPorEmail(email));
            return "redirect:/dashboard";
        }
        model.addAttribute("mensagemErro", resultado);
        return "index";
    }

    @GetMapping("/registrar-vacina")
    public String exibirForm(Model model) {
        model.addAttribute("vacinas", vacinaRepository.findAll());
        return "registrar-vacina";
    }

    @PostMapping("/registrar-vacina")
    public String registrar(@RequestParam Long vacinaId, @RequestParam String dataAplicacao, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");
        Vacina v = vacinaRepository.findById(vacinaId).orElseThrow();
        vacinaService.registrarAplicacao(u, v, 1, DoseStatus.APLICADA);
        return "redirect:/dashboard";
    }

    @GetMapping("/caderneta")
    public String caderneta(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/";

        model.addAttribute("registros", registroVacinaRepository.findByUsuario(usuario));
        model.addAttribute("dependentes", usuario.getDependentes()); 
        model.addAttribute("nomeUsuario", usuario.getNome());
        
        return "caderneta";
    }

    @PostMapping("/cadastrar")
public String processarCadastro(@RequestParam String nome, @RequestParam String email, 
                                @RequestParam String senha, Model model) {
    String resultado = authService.cadastrar(nome, email, senha);
    if (resultado.equals("Cadastro realizado com sucesso")) {
        model.addAttribute("mensagemSucesso", resultado);
        return "index";
    }
    model.addAttribute("mensagemErro", resultado);
    return "cadastro";
}

@GetMapping("/cadastrar-dependente")
    public String exibirFormDependente(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/";
        return "cadastro-dependente";
    }

@PostMapping("/cadastrar-dependente")
    public String cadastrarDependente(
            @RequestParam String nome,
            @RequestParam String dataNascimento,
            @RequestParam String parentesco,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) return "redirect:/";

        Dependente novoDependente = new Dependente();
        novoDependente.setNome(nome);
        novoDependente.setDataNascimento(LocalDate.parse(dataNascimento));
        novoDependente.setParentesco(parentesco);

        usuario.adicionarDependente(novoDependente);
        authService.cadastrar(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        
        return "redirect:/dashboard";
    }
}