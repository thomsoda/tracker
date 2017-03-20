package com.rocketleague.entity.mapped;


import com.rocketleague.refdata.WinLoss;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name = "PerformanceStatsMapping",
    classes = @ConstructorResult(
        targetClass = PerformanceStats.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "dt_played", type = Date.class),
            @ColumnResult(name = "id_game", type = Integer.class),
            @ColumnResult(name = "score", type = Integer.class),
            @ColumnResult(name = "goals", type = Integer.class),
            @ColumnResult(name = "assists", type = Integer.class),
            @ColumnResult(name = "saves", type = Integer.class),
            @ColumnResult(name = "shots", type = Integer.class),
            @ColumnResult(name = "win_loss_ind", type = String.class)
        }))
@Entity
public class PerformanceStats {
  private final String idPlayer;
  @Id
  private final Date dtPlayed;
  private final int gameId;
  private final int score;
  private final int goals;
  private final int assists;
  private final int saves;
  private final int shots;
  private final WinLoss winLoss;

  public PerformanceStats(String idPlayer, Date dtPlayed, int gameId, int score, int goals, int assists, int saves, int shots, String winLoss) {
    this.idPlayer = idPlayer;
    this.dtPlayed = dtPlayed;
    this.gameId = gameId;
    this.goals = goals;
    this.assists = assists;
    this.saves = saves;
    this.shots = shots;
    this.winLoss = WinLoss.valueOf(winLoss);
    this.score = score;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public Date getDtPlayed() {
    return dtPlayed;
  }

  public int getGameId() {
    return gameId;
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

  public WinLoss getWinLoss() {
    return winLoss;
  }

  public int getScore() {
    return score;
  }
}
