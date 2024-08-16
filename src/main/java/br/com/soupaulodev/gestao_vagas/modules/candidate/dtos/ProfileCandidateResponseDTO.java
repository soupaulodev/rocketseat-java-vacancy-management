package br.com.soupaulodev.gestao_vagas.modules.candidate.dtos;

import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;

import java.util.UUID;

public record ProfileCandidateResponseDTO (UUID id, String name, String username, String description, String email) {
    public ProfileCandidateResponseDTO(CandidateEntity candidate) {
        this(candidate.getId(), candidate.getName(), candidate.getUsername(), candidate.getDescription(), candidate.getEmail());
    }
}
