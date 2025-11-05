package com.karim_pierre_zennoune.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.karim_pierre_zennoune.memory.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // @Query(value = "SELECT * FROM users WHERE login = ?1", nativeQuery = true)
    // List<User> getUserbyLogin(String login);
}
