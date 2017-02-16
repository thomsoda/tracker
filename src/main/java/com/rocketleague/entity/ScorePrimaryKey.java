package com.rocketleague.entity;

import com.rocketleague.refdata.Team;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ScorePrimaryKey implements Serializable {
  @ManyToOne
  @JoinColumn(name = "idGame")
  private Game game;
  @Enumerated(EnumType.STRING)
  private Team team;

  public Game getGame() {
    return game;
  }

  public Team getTeam() {
    return team;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ScorePrimaryKey that = (ScorePrimaryKey) o;

    if (game != null ? !game.equals(that.game) : that.game != null) return false;
    return team == that.team;
  }

  @Override
  public int hashCode() {
    int result = game != null ? game.hashCode() : 0;
    result = 31 * result + (team != null ? team.hashCode() : 0);
    return result;
  }
}
