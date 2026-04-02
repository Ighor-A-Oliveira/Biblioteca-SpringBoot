package com.oliver.biblioteca_java.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "membros")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "autor_id")
    private Long id;

    @NotBlank
    @Column(name = "nome_membro")
    private String nome;

    @NotBlank
    @Column(name = "email_membro",unique = true)
    private String email;

    @NotBlank
    @Column(name = "telefone_membro")
    private String telefone;

    private LocalDate dataCadastro;
    private Boolean ativo;

    @OneToMany(mappedBy = "membro")
    private List<Emprestimo> emprestimos = new ArrayList<>();
}
