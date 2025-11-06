package com.karim_pierre_zennoune.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.repository.ScoreRepository;

@Service
public class ScoreService {

   @Autowired
   private ScoreRepository scoreRepository;
    
   public Score saveScore(Score score){
      return scoreRepository.save(score);
   }


   public List<Score> getScores(){
      return scoreRepository.findAll();
   }
}
