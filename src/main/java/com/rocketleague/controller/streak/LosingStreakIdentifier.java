package com.rocketleague.controller.streak;

import com.rocketleague.entity.mapped.PerformanceStats;
import com.rocketleague.refdata.WinLoss;

public class LosingStreakIdentifier implements StreakIdentifier {
  @Override
  public boolean isPartOfStreak(PerformanceStats performance) {
    return WinLoss.LOSS.equals(performance.getWinLoss());
  }
}
