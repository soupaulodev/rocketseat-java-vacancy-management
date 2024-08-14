package br.com.soupaulodev.gestao_vagas.modules.candidate.controllers;

import br.com.soupaulodev.gestao_vagas.exceptions.UserFoundException;
import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.soupaulodev.gestao_vagas.modules.candidate.useCases.DeleteCandidateUseCase;
import br.com.soupaulodev.gestao_vagas.modules.candidate.useCases.GetOneCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final GetOneCandidateUseCase getOneCandidateUseCase;
    private final DeleteCandidateUseCase deleteCandidateUseCase;

    public CandidateController(
            CreateCandidateUseCase createCandidateUseCase,
            GetOneCandidateUseCase getOneCandidateUseCase,
            DeleteCandidateUseCase deleteCandidateUseCase
    ) {
        this.createCandidateUseCase = createCandidateUseCase;
        this.getOneCandidateUseCase = getOneCandidateUseCase;
        this.deleteCandidateUseCase = deleteCandidateUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable UUID id) {

        try {
            return ResponseEntity.ok().body(getOneCandidateUseCase.execute(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateEntity candidate) {

        try {
            var result = createCandidateUseCase.execute(candidate);
            URI uri = URI.create("/candidate/" + result.getId());
            return ResponseEntity.created(uri).body(result);

        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {

        try {
            deleteCandidateUseCase.execute(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
