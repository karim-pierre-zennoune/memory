package com.karim_pierre_zennoune.memory.service;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.dto.UserAuthDto;
import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.model.Role;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

import jakarta.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, RoleService roleServ, PasswordEncoder passwEnc) {
        this.userRepository = userRepo;
        this.roleService = roleServ;
        this.passwordEncoder = passwEnc;

    }

    @PostConstruct
    void init() {
        UserAuthDto userDto = new UserAuthDto("superadmin", "123456");

        Optional<Role> optionalRole = roleService.findByName(RoleEnum.SUPER_ADMIN);
        User optionalUser = userRepository.findByLogin(userDto.login());

        if (optionalRole.isEmpty() || optionalUser != null) {
            return;
        }

        var user = new User();
        user.setLogin(userDto.login());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }

    public User registerUser(UserAuthDto user) {
        User newUser = new User(user);
        return userRepository.save(newUser);
    }

    public UserSessionDto loginUser(UserAuthDto userDto) {
        User user = null;
        user = userRepository.findByLogin(userDto.login());
        if (user != null) {
            if (user.getPassword().equals(userDto.password())) {
                return new UserSessionDto(user.getId(), user.getLogin());
            }
        }
        return null;
    }

    public ArrayList<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        ArrayList<UserDto> usersAsDto = new ArrayList<UserDto>();

        for (User user : users) {
            ArrayList<ScoreDtoForUserJoin> scoresAsDto = new ArrayList<ScoreDtoForUserJoin>();
            for (Score score : user.getScores()) {
                ScoreDtoForUserJoin scoreAsDto = new ScoreDtoForUserJoin(score.getScore(), score.getDate());
                scoresAsDto.add(scoreAsDto);
            }
            UserDto userAsDto = new UserDto(user.getId(), user.getLogin(), scoresAsDto);
            usersAsDto.add(userAsDto);
        }
        return usersAsDto;
    }

    public ArrayList<ScoreDtoForUserJoin> getUserScoresById(long id) {
        User user = userRepository.findById(id).orElseThrow();

        ArrayList<ScoreDtoForUserJoin> scoresAsDto = new ArrayList<ScoreDtoForUserJoin>();
        for (Score score : user.getScores()) {
            ScoreDtoForUserJoin scoreAsDto = new ScoreDtoForUserJoin(score.getScore(), score.getDate());
            scoresAsDto.add(scoreAsDto);
        }

        scoresAsDto.sort(Comparator.comparing(o -> -o.score()));
        return scoresAsDto;
    }

    public User createAdministrator(UserAuthDto input) {
        Optional<Role> optionalRole = roleService.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setLogin(input.login());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

}
