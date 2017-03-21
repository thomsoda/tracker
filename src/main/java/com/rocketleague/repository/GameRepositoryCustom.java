package com.rocketleague.repository;

import com.rocketleague.entity.mapped.ArenaWinStats;

import java.util.List;

public interface GameRepositoryCustom {

  List<ArenaWinStats> findArenaWinPercents(String playerId, String playlist, Boolean isRanked);
}
