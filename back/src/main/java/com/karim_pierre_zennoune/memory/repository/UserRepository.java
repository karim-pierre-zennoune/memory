package com.karim_pierre_zennoune.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // List<User> findAll();
}
