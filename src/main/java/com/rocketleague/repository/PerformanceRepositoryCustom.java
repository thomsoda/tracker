package com.rocketleague.repository;

import com.rocketleague.entity.mapped.GameSummary;
import com.rocketleague.entity.mapped.PerformanceStats;

import java.util.List;

public interface PerformanceRepositoryCustom {
  List<GameSummary> findGameSummaries(String playerId, String playlist, Boolean isRanked);
  List<PerformanceStats> findPerformances(String playerId, String playlist, Boolean isRanked);
}
