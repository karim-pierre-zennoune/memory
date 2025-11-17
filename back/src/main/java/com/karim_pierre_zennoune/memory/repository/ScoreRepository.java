package com.karim_pierre_zennoune.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karim_pierre_zennoune.memory.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop100ByOrderByScoreDesc();
}
