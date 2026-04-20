package com.oliver.biblioteca_java.exception;

public class LivroNaoDisponivelException extends RuntimeException{

    public LivroNaoDisponivelException(Long id) {
        super("O livro com id " + id + " não esta disponivel");
    }


    public LivroNaoDisponivelException() {
        super("Livro não esta disponivel");
    }
}
