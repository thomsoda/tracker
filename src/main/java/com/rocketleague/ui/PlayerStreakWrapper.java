package com.rocketleague.ui;

import java.util.List;

public class PlayerStreakWrapper {
  List<PlayerStreak> playerStreaks;

  public PlayerStreakWrapper(List<PlayerStreak> playerStreaks) {
    this.playerStreaks = playerStreaks;
  }

  public List<PlayerStreak> getPlayerStreaks() {
    return playerStreaks;
  }
}
