package com.rocketleague.controller.most;

import com.rocketleague.entity.mapped.PerformanceStats;

public interface StatGetter {
  int getTotal(PerformanceStats performance);
}
