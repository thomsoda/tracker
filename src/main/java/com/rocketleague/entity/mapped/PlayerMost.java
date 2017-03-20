package com.rocketleague.entity.mapped;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name = "PlayerMostMapping",
    classes = @ConstructorResult(
        targetClass = PlayerMost.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "total", type = Integer.class),
            @ColumnResult(name = "id_game", type = Integer.class),
            @ColumnResult(name = "dt_played", type = Date.class)
        }))
@Entity
public class PlayerMost {
  @Id
  private final String playerId;
  private int total;
  private Integer gameId;
  private Date dtPlayed;

  public PlayerMost(String playerId, int total, Integer gameId, Date dtPlayed) {
    this.playerId = playerId;
    this.total = total;
    this.gameId = gameId;
    this.dtPlayed = dtPlayed;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getTotal() {
    return total;
  }

  public Integer getGameId() {
    return gameId;
  }

  public Date getDtPlayed() {
    return dtPlayed;
  }

  public void setNewMax(int total, Integer gameId, Date dtPlayed) {
    this.total = total;
    this.gameId = gameId;
    this.dtPlayed = dtPlayed;
  }
}
