package com.oliver.biblioteca_java.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "autores")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "autor_id")
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(name = "nacionalidade_autor")
    private String nacionalidade;

    //"autores" = nome da Lista de autores na entitade livro, n tem relacao com o nome da classe autor
    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Livro> livros = new ArrayList<>();


}
