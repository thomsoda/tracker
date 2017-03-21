package com.rocketleague.ui;

import com.rocketleague.entity.mapped.ArenaWinStats;

import java.util.List;

public class ArenaWinStatsWrapper {
  private final List<ArenaWinStats> arenaWinStats;

  public ArenaWinStatsWrapper(List<ArenaWinStats> arenaWinStats) {
    this.arenaWinStats = arenaWinStats;
  }

  public List<ArenaWinStats> getArenaWinStats() {
    return arenaWinStats;
  }
}
