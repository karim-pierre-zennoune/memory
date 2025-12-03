package com.karim_pierre_zennoune.memory.exception;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongTargetException extends ResponseStatusException {
    public WrongTargetException() {
        super(HttpStatus.BAD_REQUEST, "This action is not possible on this target");
    }
}
