package com.karim_pierre_zennoune.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karim_pierre_zennoune.memory.model.Role;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
    // Role findByName(RoleEnum name);
}