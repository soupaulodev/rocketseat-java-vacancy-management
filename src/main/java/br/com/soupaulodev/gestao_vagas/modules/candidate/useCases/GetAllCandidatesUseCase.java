package br.com.soupaulodev.gestao_vagas.modules.candidate.useCases;

import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCandidatesUseCase {

    private final CandidateRepository candidateRepository;

    public GetAllCandidatesUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateEntity> execute() {
        return this.candidateRepository.findAll();
    }
}
