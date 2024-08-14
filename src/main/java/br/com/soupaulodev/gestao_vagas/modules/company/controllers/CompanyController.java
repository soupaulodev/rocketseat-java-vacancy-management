package br.com.soupaulodev.gestao_vagas.modules.company.controllers;

import br.com.soupaulodev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.DeleteCompanyUseCase;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.GetCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final GetCompanyUseCase getCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    public CompanyController(
        CreateCompanyUseCase createCompanyUseCase,
        GetCompanyUseCase getCompanyUseCase,
        DeleteCompanyUseCase deleteCompanyUseCase
    ) {
        this.createCompanyUseCase = createCompanyUseCase;
        this.getCompanyUseCase = getCompanyUseCase;
        this.deleteCompanyUseCase = deleteCompanyUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody @Valid CompanyEntity companyEntity) {

        try {
            var result = this.createCompanyUseCase.execute(companyEntity);
            URI uri = URI.create("/company/" + result.getId());
            return ResponseEntity.created(uri).body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {

        try {
            return ResponseEntity.ok().body(this.getCompanyUseCase.execute(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {

        try {
            this.deleteCompanyUseCase.execute(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
