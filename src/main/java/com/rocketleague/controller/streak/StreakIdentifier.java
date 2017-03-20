package com.rocketleague.controller.streak;

import com.rocketleague.entity.mapped.PerformanceStats;

public interface StreakIdentifier {
  boolean isPartOfStreak(PerformanceStats performance);
}
