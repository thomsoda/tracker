package com.rocketleague.ui.stats.player;

import java.math.BigDecimal;

public class PlayerContributionAverages {
  private final BigDecimal goals;
  private final BigDecimal assists;
  private final BigDecimal shots;
  private final BigDecimal saves;
  private final BigDecimal mvps;

  private PlayerContributionAverages(Builder builder) {
    goals = builder.goals;
    assists = builder.assists;
    shots = builder.shots;
    saves = builder.saves;
    mvps = builder.mvps;
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

  public static class Builder {
    private BigDecimal goals;
    private BigDecimal assists;
    private BigDecimal shots;
    private BigDecimal saves;
    private BigDecimal mvps;

    public Builder setGoals(BigDecimal goals) {
      this.goals = goals;
      return this;
    }

    public Builder setAssists(BigDecimal assists) {
      this.assists = assists;
      return this;
    }

    public Builder setShots(BigDecimal shots) {
      this.shots = shots;
      return this;
    }

    public Builder setSaves(BigDecimal saves) {
      this.saves = saves;
      return this;
    }

    public Builder setMvps(BigDecimal mvps) {
      this.mvps = mvps;
      return this;
    }

    public PlayerContributionAverages build() {
      return new PlayerContributionAverages(this);
    }
  }
}
