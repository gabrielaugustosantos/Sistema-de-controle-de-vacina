package vacinahub.service;

import java.time.LocalDate;
import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;

public class VacinaService {

    public RegistroVacina registrarAplicacao(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        return new RegistroVacina(usuario, vacina, doseAtual, status);
    }

    // Regra de negócio não-trivial
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
            
            return proximaDose;
        }
        
        return null;
    }
}