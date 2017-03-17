package com.rocketleague.ui;

import com.rocketleague.entity.mapped.GameStats;

import java.util.List;

public class GameStatsWrapper {
  List<GameStats> gameStats;

  public GameStatsWrapper(List<GameStats> gameStats) {
    this.gameStats = gameStats;
  }

  public List<GameStats> getGameStats() {
    return gameStats;
  }
}
