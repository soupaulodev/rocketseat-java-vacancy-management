package br.com.soupaulodev.gestao_vagas.modules.candidate.controllers;

import br.com.soupaulodev.gestao_vagas.modules.candidate.dtos.AuthCandidateRequestDTO;
import br.com.soupaulodev.gestao_vagas.modules.candidate.dtos.AuthCandidateResponseDTO;
import br.com.soupaulodev.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private AuthCandidateUseCase authCandidateUseCase;

    public AuthCandidateController(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @RequestMapping("/auth")
    public ResponseEntity<AuthCandidateResponseDTO> authCandidate(AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            return ResponseEntity.ok().body(this.authCandidateUseCase.execute(authCandidateRequestDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthCandidateResponseDTO(e.getMessage()));
        }

    }
}
