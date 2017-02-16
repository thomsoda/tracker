package com.rocketleague.entity;

import com.rocketleague.refdata.Team;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Performance {

  @EmbeddedId
  private PerformancePrimaryKey primaryKey;
  @Enumerated(EnumType.STRING)
  private Team team;
  private int score;
  private int goals;
  private int assists;
  private int saves;
  private int shots;
  private boolean mvpInd;

  public PerformancePrimaryKey getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(PerformancePrimaryKey primaryKey) {
    this.primaryKey = primaryKey;
  }

  public Team getTeam() {
    return team;
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

  public Game getGame() {
    return primaryKey.getGame();
  }

  public boolean inWinningTeam() {
    return (team.equals(primaryKey.getGame().getWinningTeam()));
  }
}
