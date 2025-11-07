package com.karim_pierre_zennoune.memory.dto;

import java.sql.Date;

public record ScoreRespondeDto(
                long score,
                String login,
                Date date) {

}
