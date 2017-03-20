package com.rocketleague.controller.most;

import com.rocketleague.entity.mapped.PerformanceStats;

public class GoalsGetter implements StatGetter {

  @Override
  public int getTotal(PerformanceStats performance) {
    return performance.getGoals();
  }
}
