package com.rocketleague.ui.stats.player;

public class PlayerTotals {
  private final int goals;
  private final int assists;
  private final int shots;
  private final int saves;
  private final int mvps;
  private final int teamMvps;
  private final int hatTricks;
  private final int saviours;

  private PlayerTotals(Builder builder) {
    goals = builder.goals;
    assists = builder.assists;
    shots = builder.shots;
    saves = builder.saves;
    mvps = builder.mvps;
    teamMvps = builder.teamMvps;
    hatTricks = builder.hatTricks;
    saviours = builder.saviours;
  }

  public int getGoals() {
    return goals;
  }

  public int getAssists() {
    return assists;
  }

  public int getShots() {
    return shots;
  }

  public int getSaves() {
    return saves;
  }

  public int getMvps() {
    return mvps;
  }

  public int getTeamMvps() {
    return teamMvps;
  }

  public int getHatTricks() {
    return hatTricks;
  }

  public int getSaviours() {
    return saviours;
  }

  public static class Builder {
    private int goals;
    private int assists;
    private int shots;
    private int saves;
    private int mvps;
    private int teamMvps;
    private int hatTricks;
    private int saviours;

    public Builder goals(int goals) {
      this.goals = goals;
      return this;
    }

    public Builder assists(int assists) {
      this.assists = assists;
      return this;
    }

    public Builder shots(int shots) {
      this.shots = shots;
      return this;
    }

    public Builder saves(int saves) {
      this.saves = saves;
      return this;
    }

    public Builder mvps(int mvps) {
      this.mvps = mvps;
      return this;
    }

    public Builder teamMvps(int teamMvps) {
      this.teamMvps = teamMvps;
      return this;
    }

    public Builder hatTricks(int hatTricks) {
      this.hatTricks = hatTricks;
      return this;
    }

    public Builder saviours(int saviours) {
      this.saviours = saviours;
      return this;
    }

    public PlayerTotals build() {
      return new PlayerTotals(this);
    }
  }
}
