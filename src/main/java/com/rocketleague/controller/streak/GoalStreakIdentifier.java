package com.rocketleague.controller.streak;

import com.rocketleague.entity.mapped.PerformanceStats;

public class GoalStreakIdentifier implements StreakIdentifier {
  @Override
  public boolean isPartOfStreak(PerformanceStats performance) {
    return (performance.getGoals() > 0);
  }
}
