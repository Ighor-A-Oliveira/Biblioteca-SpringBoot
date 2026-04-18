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
@AllArgsConstructor
public class GeneroDto {
    private Long id;
    @NotBlank(message = "Eh preciso inserir o nome do genero")
    @Size(min = 3, message ="O nome do genero precisa ter pelo menos 3 caracteres")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo não pode conter números")
    private String nome;

    @NotBlank(message = "Eh preciso inserir a descricao do genero")
    @Size(min = 5, message ="A descricao precisa ter pelo menos 5 caracteres")
    private String descricao;

    private List<Long> livrosId;
}
