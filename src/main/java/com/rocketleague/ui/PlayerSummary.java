package com.rocketleague.ui;

import java.math.BigDecimal;

public class PlayerSummary {
  private final String idPlayer;
  private final int gamesPlayed;
  private final BigDecimal winPercentage;
  private final int goals;
  private final int assists;
  private final int saves;
  private final BigDecimal averageGoals;
  private final BigDecimal averageAssists;
  private final BigDecimal averageSaves;

  private PlayerSummary(Builder builder) {
    this.idPlayer = builder.idPlayer;
    this.gamesPlayed = builder.gamesPlayed;
    this.winPercentage = builder.winPercentage;
    this.goals = builder.goals;
    this.assists = builder.assists;
    this.saves = builder.saves;
    this.averageGoals = builder.averageGoals;
    this.averageAssists = builder.averageAssists;
    this.averageSaves = builder.averageSaves;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }

  public BigDecimal getWinPercentage() {
    return winPercentage;
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

  public BigDecimal getAverageGoals() {
    return averageGoals;
  }

  public BigDecimal getAverageAssists() {
    return averageAssists;
  }

  public BigDecimal getAverageSaves() {
    return averageSaves;
  }

  public static class Builder {
    private String idPlayer;
    private int gamesPlayed;
    private BigDecimal winPercentage;
    private int goals;
    private int assists;
    private int saves;
    private BigDecimal averageGoals;
    private BigDecimal averageAssists;
    private BigDecimal averageSaves;

    public Builder idPlayer(String idPlayer) {
      this.idPlayer = idPlayer;
      return this;
    }

    public Builder gamesPlayed(int gamesPlayed) {
      this.gamesPlayed = gamesPlayed;
      return this;
    }

    public Builder winPercentage(BigDecimal winPercentage) {
      this.winPercentage = winPercentage;
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

    public Builder averageGoals(BigDecimal averageGoals) {
      this.averageGoals = averageGoals;
      return this;
    }

    public Builder averageAssists(BigDecimal averageAssists) {
      this.averageAssists = averageAssists;
      return this;
    }

    public Builder averageSaves(BigDecimal averageSaves) {
      this.averageSaves = averageSaves;
      return this;
    }

    public PlayerSummary build() {
      return new PlayerSummary(this);
    }
  }
}
