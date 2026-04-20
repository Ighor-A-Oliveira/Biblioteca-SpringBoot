package com.oliver.biblioteca_java.exception;

public class AutorNaoEncontradoException extends RuntimeException{

    public AutorNaoEncontradoException(Long id) {
        super("Autor com id " + id + " náo foi encontrado no sistema");
    }

    public AutorNaoEncontradoException(String nome) {
        super("Autor com nome " + nome + " náo foi encontrado no sistema");
    }

    public AutorNaoEncontradoException() {
        super("Autor nao foi encontrado no sistema");
    }
}
