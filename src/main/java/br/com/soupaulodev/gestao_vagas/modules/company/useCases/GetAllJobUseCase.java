package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAllJobUseCase {

    private final JobRepository jobRepository;

    public GetAllJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Iterable<JobEntity> execute() {
        return this.jobRepository.findAll();
    }
}
