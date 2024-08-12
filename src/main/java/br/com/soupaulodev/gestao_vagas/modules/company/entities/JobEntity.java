package br.com.soupaulodev.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 3, message = "O campo [title] deve conter pelo menos 3 caracteres")
    private String title;

    @Length(min = 3, max = 255, message = "O campo [description] deve conter no minimo 3 e máximo 255 caracteres")
    private String description;

    @Length(min = 3, message = "O campo [level] deve conter pelo menos 3 caracteres")
    private String level;

    @Length(min = 3, max = 255, message = "O campo [benefits] deve conter no minimo 3 e máximo 255 caracteres")
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;
}