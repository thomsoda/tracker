package com.rocketleague.ui;

import java.util.List;

public class PlayerSummaryCatalog {
  private final List<PlayerSummary> playerSummaries;

  public PlayerSummaryCatalog(List<PlayerSummary> playerSummaries) {
    this.playerSummaries = playerSummaries;
  }

  public List<PlayerSummary> getPlayerSummaries() {
    return playerSummaries;
  }
}
