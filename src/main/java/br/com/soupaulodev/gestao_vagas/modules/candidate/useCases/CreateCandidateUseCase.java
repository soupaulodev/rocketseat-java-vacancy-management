package br.com.soupaulodev.gestao_vagas.modules.candidate.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCandidateUseCase(
            CandidateRepository candidateRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(
                candidateEntity.getUsername(),
                candidateEntity.getEmail()
        ).ifPresent((user) -> {
            throw new UserFoundException();
        });

        candidateEntity.setId(UUID.randomUUID());
        candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));
        candidateEntity.setCreated_at(LocalDateTime.now());

        return this.candidateRepository.save(candidateEntity);
    }
}
