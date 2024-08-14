package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserFoundException;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(
                companyEntity.getUsername(),
                companyEntity.getEmail()
        ).ifPresent((user) -> {
            throw new UserFoundException();
        });

        companyEntity.setId(UUID.randomUUID());
        companyEntity.setPassword(this.passwordEncoder.encode(companyEntity.getPassword()));
        companyEntity.setCreated_at(LocalDateTime.now());

        return this.companyRepository.save(companyEntity);
    }
}
