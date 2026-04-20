package com.oliver.biblioteca_java.exception;

public class MembroNaoEncontradoException extends RuntimeException{

    public MembroNaoEncontradoException(Long id) {
        super("Nao foi encontrado membro com o id: " + id);
    }

    public MembroNaoEncontradoException(String nome) {
        super("Nao foi encontrado membro com o nome: " + nome);
    }


    public MembroNaoEncontradoException() {
        super("Nao foi encontrado registro de membros");
    }
}
