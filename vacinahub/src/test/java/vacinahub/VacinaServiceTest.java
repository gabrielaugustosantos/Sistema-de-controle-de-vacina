package vacinahub;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vacinahub.domain.DoseStatus;
import vacinahub.domain.RegistroVacina;
import vacinahub.domain.Usuario;
import vacinahub.domain.Vacina;
import vacinahub.infra.RegistroVacinaRepository;
import vacinahub.service.VacinaService;

// Habilita o Mockito para criarmos "dublês" do banco de dados
@ExtendWith(MockitoExtension.class)
public class VacinaServiceTest {
    
    @Mock
    private RegistroVacinaRepository repository; // O Dublê do Banco

    @InjectMocks
    private VacinaService service; // O Service real que vai usar o Dublê
    
    @Test
    void deveRegistrarAplicacaoDeVacina() {
        // Setup
        Usuario usuario = new Usuario("Cleiton", "cleiton@email.com", "123123");
        Vacina vacina = new Vacina(1L, "HPV", "Adultos", 2, 6);

        // Ensinamos o dublê: "Quando pedirem para salvar, devolva o próprio objeto"
        when(repository.save(any(RegistroVacina.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        RegistroVacina registro = service.registrarAplicacao(
            usuario, vacina, LocalDate.now(), 1, DoseStatus.PENDENTE
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
        Usuario usuario = new Usuario("Mario", "mario@email.com", "12345");
        Vacina vacina = new Vacina(1L, "Febre Amarela", "Adultos", 3, 2);

        RegistroVacina dose1 = new RegistroVacina();
        dose1.setUsuario(usuario);
        dose1.setVacina(vacina);
        dose1.setDoseAtual(1);
        dose1.setDoseStatus(DoseStatus.APLICADA);
        dose1.setDataAplicacao(LocalDate.of(2026, 6, 8));
        
        // Act
        RegistroVacina proximaDose = service.agendarProximaDose(dose1);
        
        // Asserts
        assertNotNull(proximaDose);
        assertEquals(2, proximaDose.getDoseAtual());
        assertEquals(DoseStatus.PENDENTE, proximaDose.getDoseStatus());
        assertEquals(LocalDate.of(2026, 8, 8), proximaDose.getDataProximaDose());
    }
}