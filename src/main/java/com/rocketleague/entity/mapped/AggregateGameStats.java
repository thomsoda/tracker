package com.rocketleague.entity.mapped;

import javax.persistence.*;

@SqlResultSetMapping(name = "AggregateGameStatsMapping",
    classes = @ConstructorResult(
        targetClass = AggregateGameStats.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "games", type = Integer.class),
            @ColumnResult(name = "score", type = Integer.class),
            @ColumnResult(name = "goals", type = Integer.class),
            @ColumnResult(name = "assists", type = Integer.class),
            @ColumnResult(name = "saves", type = Integer.class),
            @ColumnResult(name = "shots", type = Integer.class),
            @ColumnResult(name = "hat_tricks", type = Integer.class),
            @ColumnResult(name = "playmakers", type = Integer.class),
            @ColumnResult(name = "saviours", type = Integer.class),
            @ColumnResult(name = "team_mvps", type = Integer.class),
            @ColumnResult(name = "mvps", type = Integer.class),
        }))
@Entity
public class AggregateGameStats {
  @Id
  private final String idPlayer;
  private final int games;
  private final int score;
  private final int goals;
  private final int assists;
  private final int saves;
  private final int shots;
  private final int hatTricks;
  private final int playmakers;
  private final int saviours;
  private final int teamMvps;
  private final int mvps;

  public AggregateGameStats(String idPlayer, int games, int score, int goals, int assists, int saves, int shots, int hatTricks, int playmakers, int saviours, int teamMvps, int mvps) {
    this.idPlayer = idPlayer;
    this.games = games;
    this.score = score;
    this.goals = goals;
    this.assists = assists;
    this.saves = saves;
    this.shots = shots;
    this.hatTricks = hatTricks;
    this.playmakers = playmakers;
    this.saviours = saviours;
    this.teamMvps = teamMvps;
    this.mvps = mvps;
  }

  public String getIdPlayer() {
    return idPlayer;
  }

  public int getGames() {
    return games;
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

  public int getHatTricks() {
    return hatTricks;
  }

  public int getPlaymakers() {
    return playmakers;
  }

  public int getSaviours() {
    return saviours;
  }

  public int getTeamMvps() {
    return teamMvps;
  }

  public int getMvps() {
    return mvps;
  }
}
