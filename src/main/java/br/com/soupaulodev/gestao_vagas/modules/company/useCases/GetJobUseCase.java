package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.JobNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetJobUseCase {

    JobRepository jobRepository;

    public GetJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity execute(UUID id) {
        return this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
    }
}
