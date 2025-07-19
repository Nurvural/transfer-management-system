package com.bankapp.transfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { // merkezi bir mekanizma 

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalance(InsufficientBalanceException ex) {
        // HTTP 400 Bad Request ve hata mesajını döner
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
                             // istemciden gelen istekte                   //acıklayıcı hata mesajı döner
                              //bir hata olduğunu belirtir.

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    	  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
