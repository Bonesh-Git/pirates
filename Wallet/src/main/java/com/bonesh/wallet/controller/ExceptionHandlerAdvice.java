package com.bonesh.wallet.controller;

import com.bonesh.wallet.carrier.ExceptionCarrier;
import com.bonesh.wallet.exception.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(exception = UserAlreadyExist.class)
    public ResponseEntity<ExceptionCarrier> handleException(UserAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionCarrier(e.getCode(), e.getMessage()));
    }
}
