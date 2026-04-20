package com.oliver.biblioteca_java.exception;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(Long id) {
        super("Nao foi encontrado livro com o id: " + id);
    }

    public LivroNaoEncontradoException(String titulo) {
        super("Nao foi encontrado livro com o titulo: " + titulo);
    }


    public LivroNaoEncontradoException() {
        super("Nao foi encontrado registro de livros");
    }
}
