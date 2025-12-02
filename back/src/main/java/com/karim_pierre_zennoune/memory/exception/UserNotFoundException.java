package com.karim_pierre_zennoune.memory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException() {
        // super("Invalid password", HttpStatus.UNAUTHORIZED, "password");
        super(HttpStatus.UNAUTHORIZED, "User not found");
    }
}