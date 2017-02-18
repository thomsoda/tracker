package com.rocketleague.ui;

import com.rocketleague.entity.Performance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GamePerformanceFactory {
  public List<GamePerformance> get(List<Performance> performances) {
    List<GamePerformance> gamePerformances = new ArrayList<>();
    for (Performance performance : performances) {
      GamePerformance gamePerformance = new GamePerformance.Builder()
          .idPlayer(performance.getPrimaryKey().getIdPlayer())
          .score(performance.getScore())
          .goals(performance.getGoals())
          .shots(performance.getShots())
          .saves(performance.getSaves())
          .mvpInd(performance.isMvpInd())
          .build();
      gamePerformances.add(gamePerformance);
    }
    return gamePerformances;
  }
}
