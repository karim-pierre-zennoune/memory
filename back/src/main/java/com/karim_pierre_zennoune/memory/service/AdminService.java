package com.karim_pierre_zennoune.memory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import com.karim_pierre_zennoune.memory.types.RoleEnum;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<UserSessionDto> findUsersByLikeLogin(String query) {
        List<User> users = userRepository.findByLoginLikeIgnoreCase("%" + query + "%");
        ArrayList<UserSessionDto> usersAsDto = new ArrayList<UserSessionDto>();
        for (User user : users) {
            UserSessionDto userAsDto = new UserSessionDto(user.getId(), user.getLogin());
            usersAsDto.add(userAsDto);
        }
        return usersAsDto;
    }

    @Transactional
    public String deleteUser(long id) {
        // check if user exists and is not admin
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "User not found";
        } else if (user.get().getRole().getName() != RoleEnum.USER) {
            return "Cannot delete admins and super admins with an admin account";
        }

        // long deletedRecords = userRepository.deleteById(id);
        userRepository.deleteById(id);
        // System.out.println("coucou 3333333: " + deletedRecords);
        return "maybe";
        // if (deletedRecords == 1) {

        // return "Deletion successfull";
        // }
        // return "Something went wrong";
    }
}
