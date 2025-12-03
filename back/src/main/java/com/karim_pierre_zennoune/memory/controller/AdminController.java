package com.karim_pierre_zennoune.memory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.service.AdminService;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<ArrayList<UserSessionDto>> findUsersByLikeLogin(@RequestParam String param) {
        System.out.println("search: " + param);

        return new ResponseEntity<>(adminService.findUsersByLikeLogin(param), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(long id) {
        System.out.println("alive in delete contro");
        return new ResponseEntity<>(adminService.deleteUser(id), HttpStatus.OK);
    }

}
