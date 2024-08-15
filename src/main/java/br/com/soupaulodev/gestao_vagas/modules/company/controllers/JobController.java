package br.com.soupaulodev.gestao_vagas.modules.company.controllers;

import br.com.soupaulodev.gestao_vagas.modules.company.dtos.CreateJobDTO;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.DeleteJobUseCase;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.GetAllJobUseCase;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.GetJobUseCase;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;
    private final GetJobUseCase getJobUseCase;
    private final GetAllJobUseCase getAllJobUseCase;
    private final DeleteJobUseCase deleteJobUseCase;

    public JobController(
        CreateJobUseCase createJobUseCase,
        GetJobUseCase getJobUseCase,
        GetAllJobUseCase getAllJobUseCase,
        DeleteJobUseCase deleteJobUseCase
    ) {
        this.createJobUseCase = createJobUseCase;
        this.getJobUseCase = getJobUseCase;
        this.getAllJobUseCase = getAllJobUseCase;
        this.deleteJobUseCase = deleteJobUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateJobDTO createJobDTO, ServletRequest request) {
        try {
            JobEntity jobEntity = new JobEntity();
            BeanUtils.copyProperties(createJobDTO, jobEntity);
            jobEntity.setCompanyId(UUID.fromString(request.getAttribute("company_id").toString()));

            var result = this.createJobUseCase.execute(jobEntity);
            URI uri = URI.create("/job/" + result.getId());
            return ResponseEntity.created(uri).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(this.getJobUseCase.execute(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<JobEntity>> getAll() {
        try {
            return ResponseEntity.ok().body(this.getAllJobUseCase.execute());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteJobUseCase.execute(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
