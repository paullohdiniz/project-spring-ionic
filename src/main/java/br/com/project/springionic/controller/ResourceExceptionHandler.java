package br.com.project.springionic.controller;

import br.com.project.springionic.services.exception.DataIntegrityException;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException obj, HttpServletRequest request){
        StandarError standarError = new StandarError(HttpStatus.NOT_FOUND.value(), obj.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standarError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandarError> dataIntegrityException(DataIntegrityException obj, HttpServletRequest request){
        StandarError standarError = new StandarError(HttpStatus.BAD_REQUEST.value(), obj.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standarError);
    }
}
