package com.rocketleague.ui.stats.player;

import com.rocketleague.repository.PlayerAverage;

import java.util.List;

public class PlayerAverages {
  private List<PlayerAverage> averages;

  public PlayerAverages(List<PlayerAverage> averages) {
    this.averages = averages;
  }

  public List<PlayerAverage> getAverages() {
    return averages;
  }
}
