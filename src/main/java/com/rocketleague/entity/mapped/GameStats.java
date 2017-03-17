package com.rocketleague.entity.mapped;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name = "GameStatsMapping",
    classes = @ConstructorResult(
        targetClass = GameStats.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "dt_played", type = Date.class),
            @ColumnResult(name = "id_game", type = Integer.class),
            @ColumnResult(name = "score", type = Integer.class),
            @ColumnResult(name = "goals", type = Integer.class),
            @ColumnResult(name = "assists", type = Integer.class),
            @ColumnResult(name = "saves", type = Integer.class),
            @ColumnResult(name = "shots", type = Integer.class),
            @ColumnResult(name = "hat_tricks", type = Integer.class),
            @ColumnResult(name = "playmakers", type = Integer.class),
            @ColumnResult(name = "saviours", type = Integer.class),
            @ColumnResult(name = "team_mvp_ind", type = Boolean.class),
            @ColumnResult(name = "mvp_ind", type = Boolean.class),
        }))
@Entity
public class GameStats {
  private final String idPlayer;
  @Id
  private final Date dtPlayed;
  private final int gameId;
  private final int score;
  private final int goals;
  private final int assists;
  private final int saves;
  private final int shots;
  private final int hatTricks;
  private final int playmakers;
  private final int saviours;
  private final boolean wasMvp;
  private final boolean wasTeamMvp;

  public GameStats(String idPlayer, Date dtPlayed, int gameId, int score, int goals, int assists, int saves, int shots, int hatTricks, int playmakers, int saviours, boolean wasTeamMvp, boolean wasMvp) {
    this.idPlayer = idPlayer;
    this.dtPlayed = dtPlayed;
    this.gameId = gameId;
    this.score = score;
    this.goals = goals;
    this.assists = assists;
    this.saves = saves;
    this.shots = shots;
    this.hatTricks = hatTricks;
    this.playmakers = playmakers;
    this.saviours = saviours;
    this.wasMvp = wasMvp;
    this.wasTeamMvp = wasTeamMvp;
  }

  public Date getDtPlayed() {
    return dtPlayed;
  }

  public int getGameId() {
    return gameId;
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

  public boolean isWasMvp() {
    return wasMvp;
  }

  public boolean isWasTeamMvp() {
    return wasTeamMvp;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public int getHatTricks() {
    return hatTricks;
  }

  public int getPlaymakers() {
    return playmakers;
  }

  public int getSaviours() {
    return saviours;
  }
}
