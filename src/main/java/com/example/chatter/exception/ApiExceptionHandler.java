package com.example.chatter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(){
        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(IllegalMessageTypeException.class)
    public ResponseEntity<String>handleIllegalMessageTypeException(IllegalMessageTypeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
