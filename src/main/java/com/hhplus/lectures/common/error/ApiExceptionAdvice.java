package com.hhplus.lectures.common.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResult> exceptionHandler(HttpServletRequest request, final Exception e) {
        return ResponseEntity.ofNullable(new ErrorResult(e.getMessage()));
    }
}
