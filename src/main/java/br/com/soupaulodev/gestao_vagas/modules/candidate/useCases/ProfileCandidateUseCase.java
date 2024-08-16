package br.com.soupaulodev.gestao_vagas.modules.candidate.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.dtos.ProfileCandidateResponseDTO;
import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    private CandidateRepository candidateRepository;

    public ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        CandidateEntity candidate = this.candidateRepository
                .findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        return new ProfileCandidateResponseDTO(candidate);
    }
}
