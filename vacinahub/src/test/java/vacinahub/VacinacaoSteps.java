package vacinahub;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import vacinahub.domain.Dependente;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;
import vacinahub.service.VacinaService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VacinacaoSteps {

    private Usuario usuario;
    private Vacina vacina;
    private RegistroVacina registroAtual;
    private RegistroVacina proximaDose;
    private final VacinaService vacinaService = new VacinaService();

    @Dado("que existe um usuario principal para vacinacao")
    public void queExisteUmUsuarioPrincipalParaVacinacao() {
        usuario = new Usuario("Joao", "joao@email.com", "123456");
    }

    @Quando("cadastro o dependente {string} nascido em {string} com parentesco {string}")
    public void cadastroODependenteNascidoEmComParentesco(String nome, String dataNascimento, String parentesco) {
        Dependente dependente = new Dependente();
        dependente.setNome(nome);
        dependente.setDataNascimento(LocalDate.parse(dataNascimento));
        dependente.setParentesco(parentesco);

        usuario.adicionarDependente(dependente);
    }

    @Entao("o usuario deve ter {int} dependente cadastrado")
    public void oUsuarioDeveTerDependenteCadastrado(int totalEsperado) {
        assertEquals(totalEsperado, usuario.getDependentes().size());
    }

    @E("o nome do dependente cadastrado deve ser {string}")
    public void oNomeDoDependenteCadastradoDeveSer(String nomeEsperado) {
        assertEquals(nomeEsperado, usuario.getDependentes().get(0).getNome());
    }

    @Dado("que existe a vacina {string} com {int} doses e intervalo de {int} meses")
    public void queExisteAVacinaComDosesEIntervaloDeMeses(String nome, int dosesNecessarias, int mesesIntervalo) {
        vacina = new Vacina(1L, nome, "Geral", dosesNecessarias, mesesIntervalo);
    }

    @E("registrei a dose {int} aplicada em {string}")
    public void registreiADoseAplicadaEm(int doseAtual, String dataAplicacao) {
        registroAtual = new RegistroVacina(vacina, doseAtual, LocalDate.parse(dataAplicacao));
    }

    @Quando("calculo a proxima dose da vacina")
    public void calculoAProximaDoseDaVacina() {
        proximaDose = vacinaService.agendarProximaDose(registroAtual);
    }

    @Entao("a proxima dose deve ser a dose {int}")
    public void aProximaDoseDeveSerADose(int doseEsperada) {
        assertEquals(doseEsperada, proximaDose.getDoseAtual());
    }

    @E("a data da proxima dose deve ser {string}")
    public void aDataDaProximaDoseDeveSer(String dataEsperada) {
        assertEquals(LocalDate.parse(dataEsperada), proximaDose.getDataProximaDose());
    }

    @E("o status da proxima dose deve ser {string}")
    public void oStatusDaProximaDoseDeveSer(String statusEsperado) {
        assertEquals(statusEsperado, proximaDose.getStatus().name());
    }

    @Entao("nenhuma proxima dose deve ser agendada")
    public void nenhumaProximaDoseDeveSerAgendada() {
        assertNull(proximaDose);
    }
}
