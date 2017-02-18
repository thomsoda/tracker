package com.rocketleague.ui;

public class GamePerformance {
  private final String idPlayer;
  private final int score;
  private final int goals;
  private final int assists;
  private final int saves;
  private final int shots;
  private final boolean mvpInd;

  private GamePerformance(Builder builder) {
    idPlayer = builder.idPlayer;
    score = builder.score;
    goals = builder.goals;
    assists = builder.assists;
    saves = builder.saves;
    shots = builder.shots;
    mvpInd = builder.mvpInd;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public int getScore() {
    return score;
  }

  public int getGoals() {
    return goals;
  }

  public int getAssists() {
    return assists;
  }

  public int getSaves() {
    return saves;
  }

  public int getShots() {
    return shots;
  }

  public boolean isMvpInd() {
    return mvpInd;
  }

  public static class Builder {
    private String idPlayer;
    private int score;
    private int goals;
    private int assists;
    private int saves;
    private int shots;
    private boolean mvpInd;

    public Builder idPlayer(String idPlayer) {
      this.idPlayer = idPlayer;
      return this;
    }

    public Builder score(int score) {
      this.score = score;
      return this;
    }

    public Builder goals(int goals) {
      this.goals = goals;
      return this;
    }

    public Builder assists(int assists) {
      this.assists = assists;
      return this;
    }

    public Builder saves(int saves) {
      this.saves = saves;
      return this;
    }

    public Builder shots(int shots) {
      this.shots = shots;
      return this;
    }

    public Builder mvpInd(boolean mvpInd) {
      this.mvpInd = mvpInd;
      return this;
    }

    public GamePerformance build() {
      return new GamePerformance(this);
    }
  }

}
