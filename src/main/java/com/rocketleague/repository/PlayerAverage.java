package com.rocketleague.repository;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(name = "PlayerAverageMapping",
  classes = @ConstructorResult(
      targetClass = PlayerAverage.class,
      columns = {
      @ColumnResult(name = "id_player"),
      @ColumnResult(name = "games", type = Integer.class),
      @ColumnResult(name = "total", type = Integer.class),
      @ColumnResult(name = "average", type = BigDecimal.class)
  }))
@Entity
public class PlayerAverage {
  @Id
  private String playerId;
  private int games;
  private int total;
  private BigDecimal average;

  public PlayerAverage(String playerId, int games, int total, BigDecimal average) {
    this.playerId = playerId;
    this.games = games;
    this.total = total;
    this.average = average;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getGames() {
    return games;
  }

  public int getTotal() {
    return total;
  }

  public BigDecimal getAverage() {
    return average;
  }
}
