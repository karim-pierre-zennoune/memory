package com.karim_pierre_zennoune.memory.dto;

import java.sql.Date;

public record ScoreDtoForInsert(
                long score,
                long ownerId,
                Date date) {
}
