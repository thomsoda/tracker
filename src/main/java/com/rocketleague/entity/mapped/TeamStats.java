package com.rocketleague.entity.mapped;

import javax.persistence.*;

@SqlResultSetMapping(name = "TeamStatsMapping",
    classes = @ConstructorResult(
        targetClass = TeamStats.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "games", type = Integer.class),
            @ColumnResult(name = "won", type = Integer.class),
            @ColumnResult(name = "lost", type = Integer.class),
            @ColumnResult(name = "goals_for", type = Integer.class),
            @ColumnResult(name = "goals_against", type = Integer.class),
            @ColumnResult(name = "goal_difference", type = Integer.class),
            @ColumnResult(name = "clean_sheets", type = Integer.class),
        }))
@Entity
public class TeamStats {
  @Id
  private final String playerId;
  private final int games;
  private final int won;
  private final int lost;
  private final int goalsFor;
  private final int goalsAgainst;
  private final int goalDifference;
  private final int cleanSheets;

  public TeamStats(String playerId, int games, int won, int lost, int goalsFor, int goalsAgainst, int goalDifference, int cleanSheets) {
    this.playerId = playerId;
    this.games = games;
    this.won = won;
    this.lost = lost;
    this.goalsFor = goalsFor;
    this.goalsAgainst = goalsAgainst;
    this.goalDifference = goalDifference;
    this.cleanSheets = cleanSheets;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getGames() {
    return games;
  }

  public int getWon() {
    return won;
  }

  public int getLost() {
    return lost;
  }

  public int getGoalsFor() {
    return goalsFor;
  }

  public int getGoalsAgainst() {
    return goalsAgainst;
  }

  public int getGoalDifference() {
    return goalDifference;
  }

  public int getCleanSheets() {
    return cleanSheets;
  }
}
