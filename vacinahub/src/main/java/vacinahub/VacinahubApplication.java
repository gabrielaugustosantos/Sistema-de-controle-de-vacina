package vacinahub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vacinahub.domain.Vacina;
import vacinahub.infra.VacinaRepository;

@SpringBootApplication
public class VacinahubApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacinahubApplication.class, args);
    }

    // Gatilho que roda assim que o sistema inicia
    @Bean
    CommandLineRunner initDatabase(VacinaRepository vacinaRepository) {
        return args -> {
            // Se o banco estiver vazio, ele insere as vacinas padrão
            if (vacinaRepository.count() == 0) {
                vacinaRepository.save(new Vacina(null, "Febre Amarela", "Geral", 2, 120));
                vacinaRepository.save(new Vacina(null, "Hepatite B", "Geral", 3, 1));
                vacinaRepository.save(new Vacina(null, "Triplice Viral", "Geral", 2, 1));
                vacinaRepository.save(new Vacina(null, "COVID-19", "Geral", 2, 6));
            }
        };
    }
}