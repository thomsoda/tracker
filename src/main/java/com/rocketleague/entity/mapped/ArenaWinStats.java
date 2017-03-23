package com.rocketleague.entity.mapped;

import com.rocketleague.refdata.Arena;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(name = "ArenaWinStatsMapping",
    classes = @ConstructorResult(
        targetClass = ArenaWinStats.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "arena", type = String.class),
            @ColumnResult(name = "played", type = Integer.class),
            @ColumnResult(name = "wins", type = Integer.class),
            @ColumnResult(name = "losses", type = Integer.class),
            @ColumnResult(name = "win_percent", type = BigDecimal.class)
        }))
@Entity
public class ArenaWinStats {
  private final String playerId;
  @Id
  private final String arena;
  private final int games;
  private final int wins;
  private final int losses;
  private final BigDecimal winPercent;

  public ArenaWinStats(String playerId, String arena, int games, int wins, int losses, BigDecimal winPercent) {
    this.playerId = playerId;
    this.arena = Arena.valueOf(arena).getNameForUi();
    this.games = games;
    this.wins = wins;
    this.losses = losses;
    this.winPercent = winPercent;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getArena() {
    return arena;
  }

  public int getGames() {
    return games;
  }

  public int getWins() {
    return wins;
  }

  public int getLosses() {
    return losses;
  }

  public BigDecimal getWinPercent() {
    return winPercent;
  }
}
