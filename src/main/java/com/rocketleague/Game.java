package com.rocketleague;

import com.rocketleague.refdata.Arena;
import com.rocketleague.refdata.Playlist;
import com.rocketleague.refdata.Team;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

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
}
