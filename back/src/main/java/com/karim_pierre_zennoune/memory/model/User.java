package com.karim_pierre_zennoune.memory.model;


import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private int score;

    
}
