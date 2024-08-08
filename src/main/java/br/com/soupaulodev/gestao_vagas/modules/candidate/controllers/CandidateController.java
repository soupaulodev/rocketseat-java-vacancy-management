package br.com.soupaulodev.gestao_vagas.modules.candidate.controllers;

import br.com.soupaulodev.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.soupaulodev.gestao_vagas.modules.candidate.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateEntity>> getAll() {
        return ResponseEntity.ok().body(candidateService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateEntity> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(candidateService.getOne(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CandidateEntity> create(@RequestBody @Valid CandidateEntity candidate) {
        URI uri = URI.create("/candidate/" + candidate.getId());
        return ResponseEntity.created(uri).body(candidateService.create(candidate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        candidateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
