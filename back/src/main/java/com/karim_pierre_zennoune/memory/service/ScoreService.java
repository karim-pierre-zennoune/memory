package com.karim_pierre_zennoune.memory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
import com.karim_pierre_zennoune.memory.exception.UserNotFoundException;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.ScoreRepository;
import com.karim_pierre_zennoune.memory.repository.UserRepository;

@Service
public class ScoreService {

   public ScoreService(ScoreRepository scoreRepo, UserRepository userRepo) {
      scoreRepository = scoreRepo;
      userRepository = userRepo;
   }

   private final ScoreRepository scoreRepository;
   private final UserRepository userRepository;

   public Score saveScore(ScoreDtoForInsert scoreDto) {
      Score score = new Score();
      Optional<User> optionalUser = userRepository.getReferenceById(scoreDto.ownerId());
      User user = optionalUser.orElseThrow(() -> new UserNotFoundException());
      score.setOwner(user);
      score.setScore(scoreDto.score());
      score.setDate(scoreDto.date());
      return scoreRepository.save(score);
   }

   public List<ScoreDto> getLeaderboard() {

      List<Score> scores = scoreRepository.findTop100ByOrderByScoreDesc();
      ArrayList<ScoreDto> scoresAsDto = new ArrayList<ScoreDto>();

      for (Score score : scores) {
         ScoreDto scoreAsDto = new ScoreDto(score.getScore(), score.getDate(), score.getOwner().getLogin());
         scoresAsDto.add(scoreAsDto);
      }

      return scoresAsDto;
   }
}
