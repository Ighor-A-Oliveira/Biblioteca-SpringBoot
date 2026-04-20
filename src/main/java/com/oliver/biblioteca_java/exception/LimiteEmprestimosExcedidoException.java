package com.oliver.biblioteca_java.exception;

public class LimiteEmprestimosExcedidoException extends RuntimeException{

    public LimiteEmprestimosExcedidoException(Long id) {
        super("Membro com id "+ id +" ja atingiu o limite de emprestimo de livros");
    }


    public LimiteEmprestimosExcedidoException() {
        super("Este membro ja atingiu o limite de emprestimo de livros");
    }
}
