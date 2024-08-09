package br.com.soupaulodev.gestao_vagas.modules.candidate.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public DeleteCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void execute(UUID id) {
        this.candidateRepository.findById(id).orElseThrow(UserNotFoundException::new);

        this.candidateRepository.deleteById(id);
    }
}
