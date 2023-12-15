package com.rh.rhze.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ValidacaoExcpetion.class)
    public ResponseEntity<String> handleExceptionValidation(ValidacaoExcpetion excpetion){
        return ResponseEntity.badRequest().body("Erro de validação : " + excpetion.getMessage());
    }

}
