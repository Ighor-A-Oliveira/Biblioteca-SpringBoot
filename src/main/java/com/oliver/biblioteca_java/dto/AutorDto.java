package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AutorDto {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String nacionalidade;
    private List<Long> livrosId;

    public AutorDto(Long id, @NotBlank String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;

    }

    public AutorDto(Long id, @NotBlank String nome, String nacionalidade, List<Long> livrosId) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.livrosId = livrosId;

    }
}
