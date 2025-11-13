package com.karim_pierre_zennoune.memory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
import com.karim_pierre_zennoune.memory.dto.ScoreRespondeDto;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.model.User;
import com.karim_pierre_zennoune.memory.service.ScoreService;

@RestController
public class ScoreController {

  @Autowired
  private ScoreService scoreService;

  @PostMapping("/addscore")
  public ResponseEntity<Score> saveScore(@RequestBody ScoreDtoForInsert score) {
    Score savedScore = scoreService.saveScore(score);
    return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
  }

  @GetMapping("/scores")
  public List<ScoreDto> getScores() {
    System.out.println("in /scores controller");
    return scoreService.getScores();
  }
}
