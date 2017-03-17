package com.rocketleague.repository;

import com.rocketleague.entity.mapped.AggregateGameStats;
import com.rocketleague.entity.mapped.GameStats;
import com.rocketleague.entity.mapped.TeamStats;

import java.util.List;

public interface FormRepositoryCustom {
  List<AggregateGameStats> findAggregatedStatsForLastXGames(String playerId, String playlist, Boolean isRanked);
  List<GameStats> findStatsForLastXGames(String playerId, String playlist, Boolean isRanked);
  List<TeamStats> findTeamStatsForLastXGames(String playerId, String playlist, Boolean isRanked);
}
