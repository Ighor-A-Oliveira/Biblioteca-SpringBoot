package com.oliver.biblioteca_java.exception;

public class DevolucaoInvalidaException extends RuntimeException{

    public DevolucaoInvalidaException(Long id) {
        super("Emprestimo com id " + id + " ja consta como devolvido");
    }


    public DevolucaoInvalidaException() {
        super("Emprestimo ja consta como devolvido");
    }
}
