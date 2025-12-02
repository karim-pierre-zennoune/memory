package com.karim_pierre_zennoune.memory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.karim_pierre_zennoune.memory.dto.ErrorDto;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto> handleAllException(Exception ex, HttpServletRequest req) {
        var response = switch (ex) {
            case UserNotFoundException e ->
                new ErrorDto(e.getStatusCode().value(), e.getMessage());

            default -> new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown internal server error.");
        };
        return ResponseEntity.status(response.status()).body(response);
    }
}