package com.karim_pierre_zennoune.memory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karim_pierre_zennoune.memory.dto.ScoreDto;
import com.karim_pierre_zennoune.memory.dto.ScoreDtoForInsert;
import com.karim_pierre_zennoune.memory.model.Score;
import com.karim_pierre_zennoune.memory.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ScoreController {

  public ScoreController(ScoreService scoreServ) {
    scoreService = scoreServ;
  }

  private ScoreService scoreService;

  @PostMapping("/addscore")
  public ResponseEntity<Score> saveScore(@RequestBody ScoreDtoForInsert score) {
    Score savedScore = scoreService.saveScore(score);
    return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
  }

  @GetMapping("/leaderboard")
  public ResponseEntity<List<ScoreDto>> getLeaderboard() {
    return new ResponseEntity<>(scoreService.getLeaderboard(), HttpStatus.OK);
  }

}
