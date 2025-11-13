package com.karim_pierre_zennoune.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.model.User;
// import org.springframework.data.jpa.repository.Query;

// import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // @Query(value = "SELECT * FROM users WHERE login = ?1", nativeQuery = true)
    // List<User> getUserbyLogin(String login);

    List<User> findAll();
}
