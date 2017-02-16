package com.rocketleague.entity;

import com.rocketleague.refdata.Team;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
class Score {
  @EmbeddedId
  private ScorePrimaryKey primaryKey;
  private int score;

  public ScorePrimaryKey getPrimaryKey() {
    return primaryKey;
  }

  public int getScore() {
    return score;
  }

  public Team getTeam() {
    return primaryKey.getTeam();
  }
}
