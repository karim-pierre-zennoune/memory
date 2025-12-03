package com.karim_pierre_zennoune.memory.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.exception.RoleNotFoundException;
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

    public UserSessionDto promoteUserToAdmin(long id)
            throws UserNotFoundException, WrongTargetException, RoleNotFoundException {

        User user = userRepository.getReferenceById(id).orElseThrow(() -> new UserNotFoundException());

        if (user.getRole().getName() != RoleEnum.USER) {
            throw new WrongTargetException();
        }

        Role adminRole = roleService.findByName(RoleEnum.ADMIN).orElseThrow(() -> new RoleNotFoundException());
        user.setRole(adminRole);

        userRepository.save(user);

        UserSessionDto userAsDto = new UserSessionDto(user.getId(), user.getLogin(), user.getRole().getName().name());
        return userAsDto;
    }

    public UserSessionDto demoteAdminToUser(long id)
            throws UserNotFoundException, WrongTargetException, RoleNotFoundException {

        User user = userRepository.getReferenceById(id).orElseThrow(() -> new UserNotFoundException());

        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new WrongTargetException();
        }

        Role userRole = roleService.findByName(RoleEnum.USER).orElseThrow(() -> new RoleNotFoundException());
        user.setRole(userRole);

        userRepository.save(user);

        UserSessionDto userAsDto = new UserSessionDto(user.getId(), user.getLogin(), user.getRole().getName().name());
        return userAsDto;
    }

    public UserSessionDto promoteAdminToRoot(long id)
            throws UserNotFoundException, WrongTargetException, RoleNotFoundException {

        User user = userRepository.getReferenceById(id).orElseThrow(() -> new UserNotFoundException());

        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new WrongTargetException();
        }

        Role rootRole = roleService.findByName(RoleEnum.SUPER_ADMIN).orElseThrow(() -> new RoleNotFoundException());
        user.setRole(rootRole);

        userRepository.save(user);

        UserSessionDto userAsDto = new UserSessionDto(user.getId(), user.getLogin(), user.getRole().getName().name());
        return userAsDto;
    }

    public UserSessionDto demoteRootToAdmin(long id)
            throws UserNotFoundException, WrongTargetException, RoleNotFoundException {

        User user = userRepository.getReferenceById(id).orElseThrow(() -> new UserNotFoundException());

        if (user.getRole().getName() != RoleEnum.SUPER_ADMIN) {
            throw new WrongTargetException();
        }

        Role adminRole = roleService.findByName(RoleEnum.ADMIN).orElseThrow(() -> new RoleNotFoundException());
        user.setRole(adminRole);

        userRepository.save(user);

        UserSessionDto userAsDto = new UserSessionDto(user.getId(), user.getLogin(), user.getRole().getName().name());
        return userAsDto;

    }

}
