package com.karim_pierre_zennoune.memory.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.exception.UserNotFoundException;
import com.karim_pierre_zennoune.memory.exception.WrongTargetException;
import com.karim_pierre_zennoune.memory.model.Role;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

@Service
public class RootService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public RootService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User promoteUserToAdmin(long id) throws UserNotFoundException, WrongTargetException {

        User user = userRepository.getReferenceById(id).orElseThrow(() -> new UserNotFoundException());

        if (user.getRole().getName() != RoleEnum.USER) {
            throw new WrongTargetException();
        }

        Role adminRole = roleService.findByName(RoleEnum.ADMIN).orElseThrow();

        // user.get().setRole(null);
        user.setRole(adminRole);
        return user;
    }

    // public User promoteAdminToRoot() {

    // }

}
