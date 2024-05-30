package itmo.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "itmo.tech.repositories")
public class OwnerMicroServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(OwnerMicroServiceApplication.class, args);
    }
}
