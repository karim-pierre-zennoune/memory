package com.karim_pierre_zennoune.memory.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karim_pierre_zennoune.memory.service.RootService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("root")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class RootController {

    private final RootService rootService;

    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    // promote user to admin
    @GetMapping("promote-user")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    // demote admin to user

    // promote admin to root

    // demote root to admin
}
