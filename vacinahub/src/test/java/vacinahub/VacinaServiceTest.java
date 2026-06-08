package vacinahub;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;
import vacinahub.service.VacinaService;

public class VacinaServiceTest {
    
    @Test
    void deveRegistrarAplicacaoDeVacina() {
        // Setup
        VacinaService service = new VacinaService();
        
        Usuario usuario = new Usuario(
            "Cleiton",
            "cleiton@email.com",
            "123123"
        );

        Vacina vacina = new Vacina(
            1L,
            "HPV",
            "Adultos",
            2,
            6
        );

        // Act
        RegistroVacina registro = 
            service.registrarAplicacao(
                usuario,
                vacina,
                1,
                DoseStatus.PENDENTE
            );
        
        // Assert
        assertNotNull(registro);
        assertEquals(usuario, registro.getUsuario());
        assertEquals(vacina, registro.getVacina());
        assertEquals(1, registro.getDoseAtual());
        assertEquals(DoseStatus.PENDENTE, registro.getDoseStatus());
    }

    @Test
    void deveAgendarProximaDose(){
        
        // Setup
        VacinaService service = new VacinaService();

        Usuario usuario = new Usuario(
            "Mario",
            "mario@email.com",
            "12345"
        );

        Vacina vacina = new Vacina(
            1L,
            "Febre Amarela",
            "Adultos",
            3,
            2
        );

        RegistroVacina dose1 = 
        service.registrarAplicacao(
            usuario,
            vacina,
            1,
            DoseStatus.APLICADA
        );
        dose1.setDataAplicacao(LocalDate.of(2026, 6, 8));
        
        // Act
        RegistroVacina proximaDose = 
            service.agendarProximaDose(dose1);
        
        // Asserts
        assertNotNull(proximaDose);
        assertEquals(2, proximaDose.getDoseAtual());
        assertEquals(DoseStatus.PENDENTE, proximaDose.getDoseStatus());

        assertEquals(
                LocalDate.of(2026, 8, 8),
                proximaDose.getDataProximaDose());
    }
}