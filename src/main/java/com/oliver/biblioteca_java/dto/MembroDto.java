package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.Email;
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
    @NotBlank
    private String nome;
    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank
    private String telefone;
    private Integer qntEmprestimo;
    private LocalDate dataCadastro;
    private Boolean ativo;
    private List<Long> emprestimosId;

    public MembroDto(Long id, @NotBlank String nome, @NotBlank String email, @NotBlank String telefone, Integer qntEmprestimo, LocalDate dataCadastro, Boolean ativo, List<Long> emprestimosId) {
        this.id = id;
        this.nome=nome;
        this.email=email;
        this.telefone=telefone;
        this. qntEmprestimo= qntEmprestimo;
        this.dataCadastro=dataCadastro;
        this.ativo=ativo;
        this.emprestimosId=emprestimosId;

    }
}
