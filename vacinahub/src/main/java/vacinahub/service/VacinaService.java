package vacinahub.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;
import vacinahub.infra.RegistroVacinaRepository;

@Service
public class VacinaService {

    @Autowired
    private RegistroVacinaRepository registroVacinaRepository;

    public RegistroVacina registrarAplicacao(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        RegistroVacina registro = new RegistroVacina(usuario, vacina, doseAtual, status);
        return registroVacinaRepository.save(registro); // Persiste a aplicação da vacina no banco
    }

    public RegistroVacina agendarProximaDose(RegistroVacina doseAtual) {
        Vacina vacina = doseAtual.getVacina();

        if (doseAtual.getDoseAtual() < vacina.getDosesNecessarias()) {
            
            LocalDate proximaData = doseAtual.getDataAplicacao().plusMonths(vacina.getMesesIntervalo());
            
            RegistroVacina proximaDose = new RegistroVacina(
                doseAtual.getUsuario(), 
                vacina, 
                doseAtual.getDoseAtual() + 1, 
                DoseStatus.PENDENTE
            );
            proximaDose.setDataProximaDose(proximaData);
            
            return registroVacinaRepository.save(proximaDose); // Persiste o agendamento futuro no banco
        }
        
        return null;
    }
}