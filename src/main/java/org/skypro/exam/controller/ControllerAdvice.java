package org.skypro.exam.controller;

import org.skypro.exam.error.CommonError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonError> RuntimeExceptionHandler
            (Throwable e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonError("COMMON_ERROR", e.getMessage()));
    }
}
