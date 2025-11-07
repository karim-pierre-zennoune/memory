package com.karim_pierre_zennoune.memory.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "scores")
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "score", nullable = false)
    private long score;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    // @JsonIgnoreProperties("scores")
    @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "id")
    private User owner;
    // private long ownerId;

}
