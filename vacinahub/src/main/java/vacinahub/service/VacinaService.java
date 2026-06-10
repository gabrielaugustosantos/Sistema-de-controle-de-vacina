package vacinahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import vacinahub.domain.*;
import vacinahub.infra.RegistroVacinaRepository;

/**
 * Serviço responsável por gerenciar o ciclo vacinal, processar aplicações
 * e projetar agendamentos futuros.
 */
@Service
public class VacinaService {

    @Autowired
    private RegistroVacinaRepository registroVacinaRepository;

    /**
     * Analisa o status atual e projeta a próxima dose do ciclo vacinal, se aplicável.
     * @param registroAtual Dose que acabou de ser aplicada.
     * @return RegistroVacina preenchido para o futuro, ou null se o ciclo foi concluído.
     */
    public RegistroVacina agendarProximaDose(RegistroVacina registroAtual) {
        Vacina vacina = registroAtual.getVacina();
        
        // Regra de parada (Se for dose única ou atingiu o limite de doses)
        if (vacina.getMesesIntervalo() <= 0 || registroAtual.getDoseAtual() >= vacina.getDosesNecessarias()) {
            return null;
        }

        RegistroVacina proxima = new RegistroVacina();
        proxima.setUsuario(registroAtual.getUsuario());
        proxima.setVacina(vacina);
        proxima.setDoseAtual(registroAtual.getDoseAtual() + 1);
        proxima.setDoseStatus(DoseStatus.PENDENTE);
        
        // Processa o cálculo exato somando o intervalo em meses
        LocalDate dataCalculada = registroAtual.getDataAplicacao().plusMonths(vacina.getMesesIntervalo());
        proxima.setDataProximaDose(dataCalculada);
        
        return proxima;
    }

    /**
     * Registra a dose atual tomada pelo usuário e dispara a projeção do agendamento futuro.
     * @return RegistroVacina da dose aplicada e salva.
     */
    public RegistroVacina registrarAplicacao(Usuario usuario, Vacina vacina, LocalDate dataAplicacao, int doseAtual, DoseStatus status) {
        RegistroVacina registro = new RegistroVacina();
        registro.setUsuario(usuario);
        registro.setVacina(vacina);
        registro.setDoseAtual(doseAtual);
        registro.setDoseStatus(status);
        registro.setDataAplicacao(dataAplicacao);
        
        registro = registroVacinaRepository.save(registro);

        // Dispara a automação para calcular o agendamento da próxima dose
        RegistroVacina proxima = agendarProximaDose(registro);
        if (proxima != null) {
            registroVacinaRepository.save(proxima);
        }

        return registro;
    }
}