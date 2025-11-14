package com.karim_pierre_zennoune.memory.service;

import com.karim_pierre_zennoune.memory.dto.ScoreDtoForUserJoin;
import com.karim_pierre_zennoune.memory.dto.UserDto;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserService(UserRepository userRepo) {
        userRepository = userRepo;

    }

    // @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
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

}
