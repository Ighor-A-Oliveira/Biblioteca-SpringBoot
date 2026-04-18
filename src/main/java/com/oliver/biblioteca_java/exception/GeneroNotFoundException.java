package com.oliver.biblioteca_java.exception;

public class GeneroNotFoundException  extends RuntimeException{

    public GeneroNotFoundException(Long id) {
        super("Gênero não encontrado com o id: " + id);
    }

    public GeneroNotFoundException(String nome) {
        super("Gênero não encontrado com o nome: " + nome);
    }

    public GeneroNotFoundException() {
        super("Nenhum genero foi encontrado");
    }
}
