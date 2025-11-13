package com.karim_pierre_zennoune.memory.dto;

import java.util.List;

public record UserDto(
                long id,
                String login,
                List<ScoreDtoForUserJoin> scores) {

}
