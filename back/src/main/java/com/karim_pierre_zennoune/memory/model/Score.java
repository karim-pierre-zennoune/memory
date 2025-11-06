package com.karim_pierre_zennoune.memory.model;

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

   @ManyToOne
   @JoinColumn(name = "owner_id", nullable = false, referencedColumnName = "id")
   private User ownerId;
//    private long ownerId;


}
