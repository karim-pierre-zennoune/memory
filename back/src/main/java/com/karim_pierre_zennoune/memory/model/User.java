package com.karim_pierre_zennoune.memory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// import com.karim_pierre_zennoune.memory.model.Score;

@Entity
@Table(name = "users")
// @Data

@Setter
@Getter
// @AllArgsConstructor
// @NoArgsConstructor
@RequiredArgsConstructor
// @ToString

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // @JsonManagedReference
    // @JsonIgnoreProperties("owner")
    private List<Score> scores;

}
