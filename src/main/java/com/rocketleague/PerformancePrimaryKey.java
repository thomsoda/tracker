package com.rocketleague;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PerformancePrimaryKey implements Serializable {
  @ManyToOne
  @JoinColumn(name = "idGame")
  private Game game;
  private String idPlayer;

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public void setIdPlayer(String idPlayer) {
    this.idPlayer = idPlayer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PerformancePrimaryKey that = (PerformancePrimaryKey) o;

    if (!game.equals(that.game)) return false;
    return idPlayer.equals(that.idPlayer);
  }

  @Override
  public int hashCode() {
    int result = game.hashCode();
    result = 31 * result + idPlayer.hashCode();
    return result;
  }
}
