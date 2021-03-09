package br.com.project.springionic.controller;

import br.com.project.springionic.services.exception.DataIntegrityException;
import br.com.project.springionic.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandError> objectNotFound(ObjectNotFoundException obj, HttpServletRequest request){
        StandError standError = new StandError(HttpStatus.NOT_FOUND.value(), obj.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandError> dataIntegrityException(DataIntegrityException obj, HttpServletRequest request){
        StandError standError = new StandError(HttpStatus.BAD_REQUEST.value(), obj.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandError> methodArgumentNotValidException(MethodArgumentNotValidException obj, HttpServletRequest request){
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validaçao", System.currentTimeMillis());

        for(FieldError fieldError : obj.getBindingResult().getFieldErrors()){
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
