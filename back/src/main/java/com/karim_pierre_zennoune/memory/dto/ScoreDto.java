package com.karim_pierre_zennoune.memory.dto;

import java.sql.Date;

// id 

// score 
// date 
// owner

public record ScoreDto(
        long score,
        Date date,
        String owner) {
}