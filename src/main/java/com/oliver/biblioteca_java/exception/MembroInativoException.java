package com.oliver.biblioteca_java.exception;

public class MembroInativoException extends RuntimeException{

    public MembroInativoException(Long id) {
        super("Membro com id " + id + " esta com a conta inativa");
    }

    public MembroInativoException(String nome) {
        super("Membro com nome " + nome + " esta com a conta inativa");
    }


    public MembroInativoException() {
        super("Membro esta com a conta inativa");
    }
}
