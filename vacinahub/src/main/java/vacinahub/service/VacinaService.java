package vacinahub.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Vacina;

@Service
public class VacinaService {

    public RegistroVacina agendarProximaDose(RegistroVacina registroAtual) {
        Vacina vacina = registroAtual.getVacina();
        
        if (registroAtual.getDoseAtual() < vacina.getDosesNecessarias()) {
            LocalDate proximaData = registroAtual.getDataAplicacao()
                                    .plusMonths(vacina.getMesesIntervalo());
            
            RegistroVacina proximoAgendamento = new RegistroVacina(vacina, 
                registroAtual.getDoseAtual() + 1, null);
            
            proximoAgendamento.setDataProximaDose(proximaData);
            proximoAgendamento.setStatus(DoseStatus.PENDENTE);
            
            return proximoAgendamento;
        }
        return null; 
    }
}