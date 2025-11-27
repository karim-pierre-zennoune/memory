package com.karim_pierre_zennoune.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim_pierre_zennoune.memory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    List<User> findByLoginLikeIgnoreCase(String login);

    Long deleteById(long id);

    // User findByUserName(String userName);
}
