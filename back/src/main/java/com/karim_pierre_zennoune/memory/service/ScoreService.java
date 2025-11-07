package com.karim_pierre_zennoune.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.repository.ScoreRepository;
import com.karim_pierre_zennoune.memory.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
// @AllArgsConstructor
public class ScoreService {

   public ScoreService(ScoreRepository scoreRepo, UserRepository userRepo) {
      scoreRepository = scoreRepo;
      userRepository = userRepo;
   }

   // @Autowired
   private final ScoreRepository scoreRepository;
   private final UserRepository userRepository;

   public Score saveScore(ScoreDto scoreDto) {
      // System.out.println("print scoreDto");
      // System.out.println(scoreDto);
      Score score = new Score();
      User user = userRepository.getReferenceById(scoreDto.ownerId());
      score.setOwner(user);
      score.setScore(scoreDto.score());
      score.setDate(scoreDto.date());
      // System.out.println("print score");
      // System.out.println(score);
      return scoreRepository.save(score);
   }

   public List<Score> getScores() {

      // TODO
      return scoreRepository.findAll();
   }
}
