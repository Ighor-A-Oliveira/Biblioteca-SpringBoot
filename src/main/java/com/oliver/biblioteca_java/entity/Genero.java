package com.oliver.biblioteca_java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "generos")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genero_id")
    private Long id;

    @NotBlank
    @Column(name = "nome_genero")
    private String nome;
    @Column(name = "descricao_genero")
    private String descricao;

    @OneToMany(mappedBy = "genero")
    private List<Livro> livros;
}
