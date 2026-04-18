package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LivroDto {

    private Long id;
    @NotBlank(message = "Eh preciso inserir o titulo do livro")
    @Size(min = 5, message ="O titulo precisa ter pelo menos 8 caracteres")
    private String titulo;
    @NotBlank(message = "Eh preciso inserir o codigo isbn do libro")
    @Size(min = 8, message ="O isbn precisa ter pelo menos 8 caracteres")
    private String isbn;
    @NotBlank(message = "Eh preciso inserir o ano de publicacao do livro")
    private int anoPublicacao;
    @NotBlank(message = "Eh preciso inserir a quantidade em estoque desse livro")
    @Min(value = 0, message = "A quantidade em estoque náo pode ser negativa")
    private int qntEstoque;
    @NotBlank(message = "Eh preciso inserir o genero do livro")
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