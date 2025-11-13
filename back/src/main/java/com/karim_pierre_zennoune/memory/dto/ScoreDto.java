package com.karim_pierre_zennoune.memory.dto;

import java.sql.Date;

public record ScoreDto(
                long score,
                Date date,
                String owner) {
}