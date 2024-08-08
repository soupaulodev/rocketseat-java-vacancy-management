package br.com.soupaulodev.gestao_vagas.modules.candidate.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="candidates")
public class CandidateEntity {

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

    @Length(max = 255, message = "O campo [description] deve conter no máximo 255 caracteres")
    private String description;

    @Length(max = 255, message = "O campo [curriculum] deve conter no máximo 255 caracteres")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime created_at;
}
