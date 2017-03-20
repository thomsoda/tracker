package com.rocketleague.controller.streak;

import com.rocketleague.entity.mapped.PerformanceStats;

public class DroughtStreakIdentifier implements StreakIdentifier {
  @Override
  public boolean isPartOfStreak(PerformanceStats performance) {
    return (performance.getGoals() == 0);
  }
}
