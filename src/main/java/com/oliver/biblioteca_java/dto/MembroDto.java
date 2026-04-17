package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MembroDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;
    private Boolean ativo;
    private List<Long> emprestimosId;

    public MembroDto(Long id, @NotBlank String nome, @NotBlank String email, @NotBlank String telefone, LocalDate dataCadastro, Boolean ativo, List<Long> emprestimosId) {
        this.id = id;
        this.nome=nome;
        this.email=email;
        this.telefone=telefone;
        this.dataCadastro=dataCadastro;
        this.ativo=ativo;
        this.emprestimosId=emprestimosId;

    }
}
