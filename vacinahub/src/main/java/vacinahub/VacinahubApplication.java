package vacinahub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vacinahub.domain.Vacina;
import vacinahub.infra.VacinaRepository;

/**
 * Classe principal responsável por inicializar o ecossistema Spring Boot
 * e configurar o estado inicial do banco de dados (Data Seeding).
 */
@SpringBootApplication
public class VacinahubApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacinahubApplication.class, args);
    }

    /**
     * Intercepta a inicialização do sistema para verificar e popular o catálogo
     * base de vacinas caso o banco de dados em memória (H2) esteja vazio.
     * * @param vacinaRepository Repositório para persistência das vacinas.
     * @return CommandLineRunner Instância executada logo após o startup do contexto.
     */
    @Bean
    CommandLineRunner initDatabase(VacinaRepository vacinaRepository) {
        return args -> {
            // Estratégia de IDEMPOTÊNCIA: Só insere se o banco de dados estiver vazio
            if (vacinaRepository.count() == 0) {
                vacinaRepository.save(new Vacina(null, "Febre Amarela", "Geral", 2, 120));
                vacinaRepository.save(new Vacina(null, "Hepatite B", "Geral", 3, 1));
                vacinaRepository.save(new Vacina(null, "Tríplice Viral", "Geral", 2, 1));
                vacinaRepository.save(new Vacina(null, "COVID-19", "Geral", 2, 6));
            }
        };
    }
}