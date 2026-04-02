package com.oliver.biblioteca_java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "livros")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "livro_id")
    private Long id;
    @NotBlank
    private String titulo;
    @Column(name = "isbn", unique = true)
    private String isbn;
    private int anoPublicacao;
    private int qntEstaque;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToMany
    @JoinTable(
            //Nome da tabela de juncao
            name="livro_autor",
            //FK do livro
            joinColumns = @JoinColumn(name = "livro_id"),
            //FK do autor
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )//Lista de autores para casos de + de 1 autor
    private List<Autor> autores = new ArrayList<>();


    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos = new ArrayList<>();

}
