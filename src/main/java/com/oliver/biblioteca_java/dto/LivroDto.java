package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LivroDto {

    private Long id;
    private String titulo;
    private String isbn;
    private int anoPublicacao;
    private int qntEstoque;
    private Long generoId;
    private List<Long> autoresId;

    public LivroDto(Long id, @NotBlank String titulo, String isbn, Long generoId, int anoPublicacao, int qntEstoque) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.generoId = generoId;
        this.anoPublicacao = anoPublicacao;
        this.qntEstoque = qntEstoque;
    }

    public LivroDto(Long id, @NotBlank String titulo, String isbn, Long generoId, int anoPublicacao, int qntEstoque, List<Long> autoresId) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.generoId = generoId;
        this.anoPublicacao = anoPublicacao;
        this.qntEstoque = qntEstoque;
        this.autoresId = autoresId;
    }

    public LivroDto(Long id, @NotBlank String titulo, String isbn, int anoPublicacao, int qntEstoque) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.qntEstoque = qntEstoque;
    }
}