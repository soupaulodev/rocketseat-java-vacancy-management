package br.com.soupaulodev.gestao_vagas.modules.candidate.services;

import br.com.soupaulodev.gestao_vagas.exceptions.UserFoundException;
import br.com.soupaulodev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateEntity> getAll() {
        return this.candidateRepository.findAll();
    }

    public CandidateEntity getOne(UUID id) {
       return this.candidateRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public CandidateEntity create(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(
            candidateEntity.getUsername(),
            candidateEntity.getEmail()
        ).ifPresent((user) -> {
            throw new UserFoundException();
        });

        candidateEntity.setId(UUID.randomUUID());

        return this.candidateRepository.save(candidateEntity);
    }

    public void delete(UUID id) {
        this.candidateRepository.findById(id).orElseThrow(UserNotFoundException::new);

        this.candidateRepository.deleteById(id);
    }
}
