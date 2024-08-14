package br.com.soupaulodev.gestao_vagas.modules.company.useCases;

import br.com.soupaulodev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.soupaulodev.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import br.com.soupaulodev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.soupaulodev.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secret;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        CompanyEntity companyEntity = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(UserNotFoundException::new);

        boolean passwordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), companyEntity.getPassword());

        if (!passwordMatch) throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create().withIssuer("gestao-vagas")
                .withSubject(companyEntity.getId().toString())
                .sign(algorithm);

        return token;
    }
}
