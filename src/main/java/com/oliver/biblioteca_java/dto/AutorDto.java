package com.oliver.biblioteca_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorDto {

    private Long id;
    private String nome;
    private String nacionalidade;
    private List<Long> livrosId;
}
