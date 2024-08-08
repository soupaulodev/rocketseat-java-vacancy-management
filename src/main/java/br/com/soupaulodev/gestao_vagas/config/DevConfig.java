package br.com.soupaulodev.gestao_vagas.config;

import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    private final CandidateRepository candidateRepository;

    public DevConfig(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void run(String... args) {
        CandidateEntity candidateRepository = this.candidateRepository.findByUsername("username0");

        if (candidateRepository != null) {
            return;
        }

        for (int i = 0; i < 10; i++) {

            CandidateEntity candidate = new CandidateEntity(
                UUID.randomUUID(),
                "Candidate " + i,
                "username" + i,
                "email" + i + "@gmail.com",
                "password" + i,
                "description" + i,
                "curriculum" + i,
                LocalDateTime.now()
            );

            this.candidateRepository.save(candidate);
        }
    }
}
