package com.karim_pierre_zennoune.memory.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// import com.karim_pierre_zennoune.memory.model.Score;


@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;
    
}
