package com.karim_pierre_zennoune.memory.dto;

import com.karim_pierre_zennoune.memory.model.Role;

public class LoginResponse {
    private String token;
    private String login;
    private long expiresIn;
    private Role role;
    private long id;

    public LoginResponse setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public LoginResponse setLogin(String login) {
        this.login = login;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public LoginResponse setRole(Role role) {
        this.role = role;
        return this;
    }

    public Role getRole() {
        return role;
    }
}