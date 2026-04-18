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
@AllArgsConstructor
public class GeneroDto {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    private List<Long> livrosId;
}
