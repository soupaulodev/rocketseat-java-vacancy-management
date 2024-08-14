package br.com.soupaulodev.gestao_vagas.modules.company.controllers;

import br.com.soupaulodev.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import br.com.soupaulodev.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;

    public AuthCompanyController(AuthCompanyUseCase authCompanyUseCase) {
        this.authCompanyUseCase = authCompanyUseCase;
    }

    @PostMapping("/company")
    public ResponseEntity<String> create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        try {
            return ResponseEntity.ok().body(this.authCompanyUseCase.execute(authCompanyDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
