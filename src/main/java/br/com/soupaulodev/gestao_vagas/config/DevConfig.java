package br.com.soupaulodev.gestao_vagas.config;

import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    public DevConfig(
        CandidateRepository candidateRepository,
        CompanyRepository companyRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) {

        if (!this.candidateRepository.findByUsername("username0").isPresent()) {
            for (int i = 0; i < 10; i++) {

                CandidateEntity candidate = new CandidateEntity(
                        null,
                        "Candidate name " + i,
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

        if (!this.companyRepository.findByUsername("username0").isPresent()) {
            for (int i = 0; i < 10; i++) {
                CompanyEntity company = new CompanyEntity(
                        null,
                        "Company name " + i,
                        "username" + i,
                        "email" + i + "@gmail.com",
                        "password" + i,
                        "https://website" + i + ".com",
                        LocalDateTime.now()
                );

                this.companyRepository.save(company);
            }
        }
    }
}
