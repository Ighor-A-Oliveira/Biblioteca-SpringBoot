package com.oliver.biblioteca_java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "generos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "genero_id")
    private Long id;

    @Column(name = "nome_genero")
    private String nome;

    @Column(name = "descricao_genero")
    private String descricao;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Livro> livros;
}
