package com.karim_pierre_zennoune.memory.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scores")
@Setter
@Getter
@RequiredArgsConstructor

public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "score", nullable = false)
    private long score;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "id")
    private User owner;
}
