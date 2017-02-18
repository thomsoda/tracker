package com.rocketleague.ui;

import com.rocketleague.entity.Game;

import java.util.List;

public class GameDetail {

  private final List<GamePerformance> bluePerformances;
  private final List<GamePerformance> orangePerformances;

  private final int blueScore;
  private final int orangeScore;

  public GameDetail(List<GamePerformance> bluePerformances, List<GamePerformance> orangePerformances, Game game) {
    this.bluePerformances = bluePerformances;
    this.orangePerformances = orangePerformances;
    this.blueScore = game.getBlueScore();
    this.orangeScore = game.getOrangeScore();
  }

  public List<GamePerformance> getBluePerformances() {
    return bluePerformances;
  }

  public List<GamePerformance> getOrangePerformances() {
    return orangePerformances;
  }

  public int getBlueScore() {
    return blueScore;
  }

  public int getOrangeScore() {
    return orangeScore;
  }
}
