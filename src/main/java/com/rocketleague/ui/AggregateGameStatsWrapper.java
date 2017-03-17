package com.rocketleague.ui;

import com.rocketleague.entity.mapped.AggregateGameStats;

import java.util.List;

public class AggregateGameStatsWrapper {
  List<AggregateGameStats> aggregateGameStats;

  public AggregateGameStatsWrapper(List<AggregateGameStats> aggregateGameStats) {
    this.aggregateGameStats = aggregateGameStats;
  }

  public List<AggregateGameStats> getAggregateGameStats() {
    return aggregateGameStats;
  }
}
