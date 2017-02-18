package com.rocketleague.ui;

import java.util.List;

public class GameSummaryCatalog {
  private final List<GameSummary> gameSummaries;

  public GameSummaryCatalog(List<GameSummary> gameSummaries) {
    this.gameSummaries = gameSummaries;
  }

  public List<GameSummary> getGameSummaries() {
    return gameSummaries;
  }
}
