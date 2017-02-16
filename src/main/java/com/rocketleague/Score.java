package com.rocketleague;

import com.rocketleague.refdata.Team;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Score {
  private int idGame;
  @Enumerated(EnumType.STRING)
  private Team team;
  private int score;

  public int getIdGame() {
    return idGame;
  }

  public Team getTeam() {
    return team;
  }

  public int getScore() {
    return score;
  }
}
