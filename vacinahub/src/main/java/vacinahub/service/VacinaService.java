package vacinahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import vacinahub.domain.*;
import vacinahub.infra.RegistroVacinaRepository;

@Service
public class VacinaService {

    @Autowired
    private RegistroVacinaRepository registroVacinaRepository;

    // 1. MÉTODO RESTAURADO PARA OS TESTES E REGRA DE NEGÓCIO
    public RegistroVacina agendarProximaDose(RegistroVacina registroAtual) {
        Vacina vacina = registroAtual.getVacina();
        
        // Regra de parada: Se é dose única ou já atingiu o máximo de doses, não tem próxima
        if (vacina.getMesesIntervalo() <= 0 || registroAtual.getDoseAtual() >= vacina.getDosesNecessarias()) {
            return null;
        }

        RegistroVacina proxima = new RegistroVacina();
        proxima.setUsuario(registroAtual.getUsuario());
        proxima.setVacina(vacina);
        proxima.setDoseAtual(registroAtual.getDoseAtual() + 1); // Soma +1 na dose
        proxima.setDoseStatus(DoseStatus.PENDENTE); // Fica com status Pendente
        
        // Calcula a data da próxima dose
        LocalDate dataCalculada = registroAtual.getDataAplicacao().plusMonths(vacina.getMesesIntervalo());
        proxima.setDataProximaDose(dataCalculada);
        
        return proxima;
    }

    // 2. Método usado pelo WebController
    public RegistroVacina registrarAplicacao(Usuario usuario, Vacina vacina, LocalDate dataAplicacao, int doseAtual, DoseStatus status) {
        RegistroVacina registro = new RegistroVacina();
        registro.setUsuario(usuario);
        registro.setVacina(vacina);
        registro.setDoseAtual(doseAtual);
        registro.setDoseStatus(status);
        registro.setDataAplicacao(dataAplicacao);
        
        // Salva a vacina que foi tomada hoje
        registro = registroVacinaRepository.save(registro);

        // Automático: Gera o registro PENDENTE do futuro e salva no banco!
        RegistroVacina proxima = agendarProximaDose(registro);
        if (proxima != null) {
            registroVacinaRepository.save(proxima);
        }

        return registro;
    }
}