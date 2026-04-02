package com.oliver.biblioteca_java.dto;

import com.oliver.biblioteca_java.entity.Autor;
import com.oliver.biblioteca_java.entity.Emprestimo;
import com.oliver.biblioteca_java.entity.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class LivroDto {

    private Long id;
    private String titulo;
    private String isbn;
    private int anoPublicacao;
    private int qntEstaque;
    private Genero genero;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;

    public LivroDto(Long id, @NotBlank String titulo, String isbn, Genero genero, int anoPublicacao, int qntEstaque) {
    }
}
