package com.karim_pierre_zennoune.memory.dto;

import java.sql.Date;

public record ScoreDto(
                long score,
                long ownerId,
                Date date) {
}

public record ScoreRespondeDto(
                long score,
                String login,
                Date date) {

}
