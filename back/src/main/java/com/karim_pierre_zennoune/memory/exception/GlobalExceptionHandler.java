package com.karim_pierre_zennoune.memory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.karim_pierre_zennoune.memory.dto.ErrorDto;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDto> handleAllException(Exception ex, HttpServletRequest req) {
        var response = switch (ex) {
            case WrongTargetException e ->
                new ErrorDto(e.getStatusCode().value(), e.getMessage());
            case UserNotFoundException e ->
                new ErrorDto(e.getStatusCode().value(), e.getMessage());
            case RoleNotFoundException e ->
                new ErrorDto(e.getStatusCode().value(), e.getMessage());
            default -> new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown internal server error.");
        };
        return ResponseEntity.status(response.status()).body(response);
    }
}