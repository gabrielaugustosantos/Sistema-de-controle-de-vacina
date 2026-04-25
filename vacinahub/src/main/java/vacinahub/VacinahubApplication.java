package vacinahub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VacinahubApplication {

    public static void main(String[] args) {
        // Este é o comando que liga o servidor e inicia o VacinaHub
        SpringApplication.run(VacinahubApplication.class, args);
    }
}