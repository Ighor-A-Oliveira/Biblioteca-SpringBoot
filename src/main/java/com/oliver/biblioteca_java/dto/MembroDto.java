package com.oliver.biblioteca_java.dto;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Eh preciso inserir o nome do membro")
    @Size(min = 5, message ="O nome precisa ter pelo menos 5 caracteres")
    @Pattern(regexp = "^[^0-9]*$", message = "O campo não pode conter números")
    private String nome;

    @NotBlank(message = "Eh preciso inserir o email do membro")
    @Size(min = 5, message ="O email precisa ter pelo menos 5 caracteres")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Eh preciso inserir o telefone do membro")
    @Size(min = 8, message ="O telefone precisa ter pelo menos 8 caracteres")
    private String telefone;

    @Min(value = 0, message = "A quantidade disponivel de emprestimos náo pode ser negativa")
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
