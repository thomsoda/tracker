package com.rocketleague.ui;

import com.rocketleague.entity.mapped.GameSummary;

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
