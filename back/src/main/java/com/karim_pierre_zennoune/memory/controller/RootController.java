package com.karim_pierre_zennoune.memory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.RootService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/promote-user")
    public ResponseEntity<UserSessionDto> promoteUserToAdmin(@RequestBody UserSessionDto dto) {
        return ResponseEntity.ok(rootService.promoteUserToAdmin(dto.id()));
    }

    // demote admin to user
    @PostMapping("/demote-admin")
    public ResponseEntity<UserSessionDto> demoteAdminToUser(@RequestBody UserSessionDto dto) {

        return ResponseEntity.ok(rootService.demoteAdminToUser(dto.id()));
    }

    // promote admin to root
    @PostMapping("/promote-admin")
    public ResponseEntity<UserSessionDto> promoteAdminToRoot(@RequestBody UserSessionDto dto) {

        return ResponseEntity.ok(rootService.promoteAdminToRoot(dto.id()));
    }

    // demote root to admin

    @PostMapping("/demote-root")
    public ResponseEntity<UserSessionDto> demoteRootToAdmin(@RequestBody UserSessionDto dto) {

        return ResponseEntity.ok(rootService.demoteRootToAdmin(dto.id()));
    }
}
