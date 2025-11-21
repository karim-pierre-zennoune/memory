package com.karim_pierre_zennoune.memory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.karim_pierre_zennoune.memory.dto.UserLoginDto;

@Entity
@Table(name = "users")
@Setter
@Getter
@RequiredArgsConstructor

public class User {

    public User(UserLoginDto userLoginDto) {
        login = userLoginDto.login();
        password = userLoginDto.password();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Score> scores;

}
