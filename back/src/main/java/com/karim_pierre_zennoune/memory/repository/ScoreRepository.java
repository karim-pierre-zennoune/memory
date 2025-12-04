package com.karim_pierre_zennoune.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karim_pierre_zennoune.memory.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop100ByOrderByScoreDesc();
}
