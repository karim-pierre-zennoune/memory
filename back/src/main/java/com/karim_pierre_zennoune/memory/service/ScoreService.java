package com.karim_pierre_zennoune.memory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
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
      User user = userRepository.getReferenceById(scoreDto.ownerId());
      score.setOwner(user);
      score.setScore(scoreDto.score());
      score.setDate(scoreDto.date());
      return scoreRepository.save(score);
   }

   public List<ScoreDto> getScores() {

      System.out.println("in ScoreService.getScores");
      List<Score> scores = scoreRepository.findAll();
      ArrayList<ScoreDto> scoresAsDto = new ArrayList<ScoreDto>();

      for (Score score : scores) {
         ScoreDto scoreAsDto = new ScoreDto(score.getScore(), score.getDate(), score.getOwner().getLogin());
         scoresAsDto.add(scoreAsDto);
      }

      return scoresAsDto;
   }

   public List<ScoreDto> getLeaderboard() {
      System.out.println("in ScoreService.getLeaderboard");

      List<Score> scores = scoreRepository.findTop100ByOrderByScoreDesc();
      ArrayList<ScoreDto> scoresAsDto = new ArrayList<ScoreDto>();

      for (Score score : scores) {
         ScoreDto scoreAsDto = new ScoreDto(score.getScore(), score.getDate(), score.getOwner().getLogin());
         scoresAsDto.add(scoreAsDto);
      }

      return scoresAsDto;

   }
}
