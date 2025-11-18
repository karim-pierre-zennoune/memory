package com.karim_pierre_zennoune.memory.service;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.dto.UserLoginDto;
import com.karim_pierre_zennoune.memory.dto.UserSessionDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.repository.UserRepository;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    public UserService(UserRepository userRepo) {
        userRepository = userRepo;
    }

    private UserRepository userRepository;
    // private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // public User registerUser(User user) {
    // user.setPassword(encoder.encode(user.getPassword()));
    // return userRepository.save(user);
    // }

    public UserSessionDto loginUser(UserLoginDto user) {

        // TODO

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

}
