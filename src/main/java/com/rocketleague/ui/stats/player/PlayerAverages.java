package com.rocketleague.ui.stats.player;

import java.math.BigDecimal;

public class PlayerAverages {
  private final BigDecimal goals;
  private final BigDecimal assists;
  private final BigDecimal shots;
  private final BigDecimal saves;
  private final BigDecimal mvps;
  private final BigDecimal teamMvps;
  private final BigDecimal hatTricks;
  private final BigDecimal saviours;

  private PlayerAverages(Builder builder) {
    goals = builder.goals;
    assists = builder.assists;
    shots = builder.shots;
    saves = builder.saves;
    mvps = builder.mvps;
    teamMvps = builder.teamMvps;
    hatTricks = builder.hatTricks;
    saviours = builder.saviours;
  }

  public BigDecimal getGoals() {
    return goals;
  }

  public BigDecimal getAssists() {
    return assists;
  }

  public BigDecimal getShots() {
    return shots;
  }

  public BigDecimal getSaves() {
    return saves;
  }

  public BigDecimal getMvps() {
    return mvps;
  }

  public BigDecimal getTeamMvps() {
    return teamMvps;
  }

  public BigDecimal getHatTricks() {
    return hatTricks;
  }

  public BigDecimal getSaviours() {
    return saviours;
  }

  public static class Builder {
    private BigDecimal goals;
    private BigDecimal assists;
    private BigDecimal shots;
    private BigDecimal saves;
    private BigDecimal mvps;
    private BigDecimal teamMvps;
    private BigDecimal hatTricks;
    private BigDecimal saviours;

    public Builder goals(BigDecimal goals) {
      this.goals = goals;
      return this;
    }

    public Builder assists(BigDecimal assists) {
      this.assists = assists;
      return this;
    }

    public Builder shots(BigDecimal shots) {
      this.shots = shots;
      return this;
    }

    public Builder saves(BigDecimal saves) {
      this.saves = saves;
      return this;
    }

    public Builder mvps(BigDecimal mvps) {
      this.mvps = mvps;
      return this;
    }

    public Builder teamMvps(BigDecimal teamMvps) {
      this.teamMvps = teamMvps;
      return this;
    }

    public Builder hatTricks(BigDecimal hatTricks) {
      this.hatTricks = hatTricks;
      return this;
    }

    public Builder saviours(BigDecimal saviours) {
      this.saviours = saviours;
      return this;
    }

    public PlayerAverages build() {
      return new PlayerAverages(this);
    }
  }

}
