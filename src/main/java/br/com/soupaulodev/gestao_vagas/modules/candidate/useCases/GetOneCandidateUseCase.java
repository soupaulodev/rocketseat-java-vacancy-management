package br.com.soupaulodev.gestao_vagas.modules.candidate.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetOneCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public GetOneCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(UUID id) {
        return this.candidateRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
