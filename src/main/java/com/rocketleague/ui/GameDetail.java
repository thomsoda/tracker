package com.rocketleague.ui;

import java.util.List;

public class GameDetail {

  private final List<GamePerformance> bluePerformances;
  private final List<GamePerformance> orangePerformances;

  public GameDetail(List<GamePerformance> bluePerformances, List<GamePerformance> orangePerformances) {
    this.bluePerformances = bluePerformances;
    this.orangePerformances = orangePerformances;
  }

  public List<GamePerformance> getBluePerformances() {
    return bluePerformances;
  }

  public List<GamePerformance> getOrangePerformances() {
    return orangePerformances;
  }
}
