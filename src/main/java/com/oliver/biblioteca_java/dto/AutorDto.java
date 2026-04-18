package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Eh preciso inserir o nome do autor")
    @Size(min = 5, message ="O nome precisa ter pelo menos 8 caracteres")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo não pode conter números")
    private String nome;

    @NotBlank(message = "Eh preciso inserir a nacionalidade do autor")
    @Size(min = 5, message ="A nacionalidade precisa ter pelo menos 5 caracteres")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo não pode conter números")
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
