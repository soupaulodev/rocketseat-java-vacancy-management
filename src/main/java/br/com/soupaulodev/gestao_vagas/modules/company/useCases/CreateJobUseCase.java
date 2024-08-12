package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.modules.company.repositories.JobRepository;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity execute (JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
