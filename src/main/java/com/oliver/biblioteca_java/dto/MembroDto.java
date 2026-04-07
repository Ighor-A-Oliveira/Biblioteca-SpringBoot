package com.oliver.biblioteca_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembroDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;
    private Boolean ativo;
}
