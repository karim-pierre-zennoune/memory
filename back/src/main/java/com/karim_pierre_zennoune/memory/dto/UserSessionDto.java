package com.karim_pierre_zennoune.memory.dto;

import com.karim_pierre_zennoune.memory.model.Role;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

public record UserSessionDto(
        long id,
        String login,
        String role) {
}
