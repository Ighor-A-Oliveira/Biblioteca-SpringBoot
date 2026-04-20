package com.oliver.biblioteca_java.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException{

    public EmprestimoNaoEncontradoException(Long id) {
        super("Registro de devolucao não encontrado com o id: " + id);
    }


    public EmprestimoNaoEncontradoException() {
        super("Registro de devolucao não encontrado");
    }
}
