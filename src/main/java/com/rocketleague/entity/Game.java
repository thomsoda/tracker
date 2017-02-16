package com.rocketleague.entity;

import com.rocketleague.refdata.Arena;
import com.rocketleague.refdata.Playlist;
import com.rocketleague.refdata.Team;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Game {
  @Id
  private int idGame;
  @Enumerated(EnumType.STRING)
  private Team winningTeam;
  private Date dtPlayed;
  @Enumerated(EnumType.STRING)
  private Playlist playlist;
  @Enumerated(EnumType.STRING)
  private Arena arena;
  private boolean overtimeInd;
  private boolean forfeitInd;
  private boolean competitiveInd;
  @OneToMany(mappedBy = "primaryKey.game")
  private Set<Score> scores;

  public int getIdGame() {
    return idGame;
  }

  public Team getWinningTeam() {
    return winningTeam;
  }

  public Date getDtPlayed() {
    return dtPlayed;
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  public Arena getArena() {
    return arena;
  }

  public boolean isOvertimeInd() {
    return overtimeInd;
  }

  public boolean isForfeitInd() {
    return forfeitInd;
  }

  public boolean isCompetitiveInd() {
    return competitiveInd;
  }

  public int getOrangeScore() {
    return getScore(Team.ORANGE);
  }

  public int getBlueScore() {
    return getScore(Team.BLUE);
  }

  private int getScore(Team team) {
    for (Score score : scores) {
      if (team.equals(score.getTeam())) {
        return score.getScore();
      }
    }
    return 0;
  }
}
