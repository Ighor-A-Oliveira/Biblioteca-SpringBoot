package com.oliver.biblioteca_java.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Todo
        //LivroNaoDisponivelException: Quando estoque é 0
        //LivroNaoEncontradoException:
        //MembroNaoEncontradoException:
        //EmprestimoNaoEncontradoException:
        //LimiteEmprestimosExcedidoException: qnd usuario ja tem 3 emprestimos e tenta pegar o quarto
        //DevolucaoInvalidaException: tenta devolver um emprestimo que ja consta como devolvido

    @ExceptionHandler(GeneroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handleGeneroNotFoundHandler(GeneroNotFoundException exception){
        return ErrorResponse.of(HttpStatus.NOT_FOUND, exception.getMessage());
    }


    // Erros de validação em @RequestBody (ex: @Valid em DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        //Preparando uma lista com os detalhes do erro
        List<ErrorResponse.FieldError> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                //cada erro fara parte de uma lista do objeto FieldError dentro de ErrorResponse
                .map(error -> new ErrorResponse.FieldError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        //retornando a exception tratada
        return ErrorResponse.ofValidation(HttpStatus.UNPROCESSABLE_ENTITY, fieldErrors);
    }

    // Erros de validação em @RequestParam ou @PathVariable
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException exception) {

        List<ErrorResponse.FieldError> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorResponse.FieldError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                ))
                .toList();
        return ErrorResponse.ofValidation(HttpStatus.BAD_REQUEST, fieldErrors);
    }


    // Erros genéricos não mapeados
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception exception) {
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
    }

}
