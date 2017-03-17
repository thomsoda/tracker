package com.rocketleague.ui;

import com.rocketleague.entity.mapped.TeamStats;

import java.util.List;

public class TeamStatsWrapper {
  private final List<TeamStats> teamStats;

  public TeamStatsWrapper(List<TeamStats> teamStats) {
    this.teamStats = teamStats;
  }

  public List<TeamStats> getTeamStats() {
    return teamStats;
  }
}
