package com.rocketleague.repository;

import com.rocketleague.ui.GameSummary;

import java.util.List;

public interface PerformanceRepositoryCustom {

  List<GameSummary> findGameSummaries(String playerId, String playlist, Boolean isRanked);
}
