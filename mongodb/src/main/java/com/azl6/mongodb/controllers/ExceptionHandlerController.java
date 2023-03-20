package com.azl6.mongodb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.azl6.mongodb.exceptions.ObjectNotFoundException;
import com.azl6.mongodb.exceptions.StandardError;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
        StandardError erro = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> methodArgument(MethodArgumentNotValidException e){
        
        List<StandardError> erros = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(erro -> {

        String erroCaptado = erro.getDefaultMessage();

        erros.add(new StandardError(erroCaptado, HttpStatus.BAD_REQUEST.value()));

        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);

    }
    

}
