package vacinahub.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vacinahub.service.AuthService;
import vacinahub.domain.Dependente;
import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;
import vacinahub.service.VacinaService;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private AuthService authService;

    @Autowired
    private VacinaService vacinaService;

    private final List<Vacina> vacinasDisponiveis = List.of(
            new Vacina(1L, "Febre Amarela", "Geral", 2, 120),
            new Vacina(2L, "Hepatite B", "Geral", 3, 1),
            new Vacina(3L, "Triplice Viral", "Geral", 2, 1),
            new Vacina(4L, "COVID-19", "Geral", 2, 6)
    );

    @GetMapping("/")
    public String paginaInicial() {
        return "index";
    }

    @GetMapping("/cadastro")
    public String paginaCadastro() {
        return "cadastro";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }
        model.addAttribute("nomeUsuario", usuario.getNome());
        model.addAttribute("emailUsuario", usuario.getEmail());
        model.addAttribute("usuario", usuario);
        model.addAttribute("proximaDose", session.getAttribute("proximaDose"));
        return "dashboard";
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha,
                             HttpSession session, Model model) {

        String resultado = authService.login(email, senha);

        if (resultado.equals("Login realizado com sucesso")) {
            Usuario usuario = authService.buscarPorEmail(email);
            session.setAttribute("usuarioLogado", usuario);
            model.addAttribute("nomeUsuario", usuario.getNome());
            model.addAttribute("emailUsuario", usuario.getEmail());
            model.addAttribute("usuario", usuario);
            model.addAttribute("proximaDose", session.getAttribute("proximaDose"));
            return "dashboard";
        } else {
            model.addAttribute("mensagemErro", resultado);
            return "index";
        }
    }

    @PostMapping("/cadastrar")
    public String fazerCadastro(@RequestParam String nome, @RequestParam String email,
                                @RequestParam String senha, Model model) {

        String resultado = authService.cadastrar(nome, email, senha);

        if (resultado.equals("Cadastro realizado com sucesso")) {
            model.addAttribute("mensagemSucesso", "Conta criada com sucesso! Faça seu login.");
            return "index";
        } else {
            model.addAttribute("mensagemErro", resultado);
            return "cadastro";
        }
    }

    @GetMapping("/cadastrar-dependente")
    public String exibirFormDependente(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }
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
        if (usuario == null) {
            return "redirect:/";
        }

        Dependente dependente = new Dependente();
        dependente.setNome(nome);
        dependente.setDataNascimento(LocalDate.parse(dataNascimento));
        dependente.setParentesco(parentesco);

        usuario.adicionarDependente(dependente);
        session.setAttribute("usuarioLogado", usuario);

        model.addAttribute("nomeUsuario", usuario.getNome());
        model.addAttribute("usuario", usuario);
        model.addAttribute("proximaDose", session.getAttribute("proximaDose"));
        model.addAttribute("mensagemSucesso", "Dependente " + nome + " cadastrado com sucesso!");
        return "dashboard";
    }

    @GetMapping("/registrar-vacina")
    public String exibirFormVacina(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute("vacinas", vacinasDisponiveis);
        return "registrar-vacina";
    }

    @PostMapping("/registrar-vacina")
    public String registrarVacina(
            @RequestParam Long vacinaId,
            @RequestParam String dataAplicacao,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }

        Vacina vacina = buscarVacinaPorId(vacinaId);
        
        // Usando o construtor correto e amarrando à sessão do usuário logado
        RegistroVacina registroAtual = new RegistroVacina(usuario, vacina, 1, DoseStatus.APLICADA);
        registroAtual.setDataAplicacao(LocalDate.parse(dataAplicacao));
        
        RegistroVacina proximaDose = vacinaService.agendarProximaDose(registroAtual);

        session.setAttribute("proximaDose", proximaDose);

        model.addAttribute("nomeUsuario", usuario.getNome());
        model.addAttribute("emailUsuario", usuario.getEmail());
        model.addAttribute("usuario", usuario);
        model.addAttribute("proximaDose", proximaDose);
        model.addAttribute("mensagemSucesso", "Vacina registrada com sucesso!");

        return "dashboard";
    }

    private Vacina buscarVacinaPorId(Long vacinaId) {
        return vacinasDisponiveis.stream()
                .filter(vacina -> vacina.getId().equals(vacinaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vacina nao encontrada"));
    }
}