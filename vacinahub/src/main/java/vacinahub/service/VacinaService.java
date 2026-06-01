package vacinahub.service;

import org.springframework.stereotype.Service;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Vacina;
import vacinahub.domain.DoseStatus;
import java.time.LocalDate;

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