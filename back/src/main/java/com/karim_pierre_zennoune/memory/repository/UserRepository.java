package com.karim_pierre_zennoune.memory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karim_pierre_zennoune.memory.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    List<User> findByLoginLikeIgnoreCase(String login);

    Long deleteById(long id);

    Optional<User> getReferenceById(long id);
    // User getReferenceById(long id) throws Exception;
}
