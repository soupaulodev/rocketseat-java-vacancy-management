package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.JobNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteJobUseCase {

    private final JobRepository jobRepository;

    public DeleteJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(UUID id) {
        this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);

        this.jobRepository.deleteById(id);
    }
}
