package br.com.soupaulodev.gestao_vagas.modules.candidate.dtos;

import java.util.Date;

public record AuthCandidateResponseDTO(String accessToken, Date expiresAt) {
}
