package com.karim_pierre_zennoune.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim_pierre_zennoune.memory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
