package br.com.soupaulodev.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    @Pattern(message = "O campo [username] deve conter apenas letras e números", regexp = "^[a-zA-Z0-9]*$")
    @Length(min=3, message = "O campo [username] deve conter pelo menos 3 caracteres")
    private String username;

    @Email(message = "O campo [email] deve ser um email válido")
    private String email;

    @Length(min = 8, message = "O campo [password] deve conter pelo menos 8 caracteres")
    private String password;

    @URL(message = "O campo [website] deve ser uma URL válida")
    private String website;

    @CreationTimestamp
    private LocalDateTime created_at;
}
