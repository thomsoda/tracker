package com.rocketleague.entity.mapped;

import com.rocketleague.refdata.Arena;
import com.rocketleague.refdata.Playlist;
import com.rocketleague.refdata.WinLoss;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(name = "GameSummaryMapping",
    classes = @ConstructorResult(
        targetClass = GameSummary.class,
        columns = {
            @ColumnResult(name = "id_game", type = Integer.class),
            @ColumnResult(name = "playlist", type = String.class),
            @ColumnResult(name = "competitive_ind", type = Boolean.class),
            @ColumnResult(name = "dt_played", type = Date.class),
            @ColumnResult(name = "arena", type = String.class),
            @ColumnResult(name = "orange_score", type = Integer.class),
            @ColumnResult(name = "blue_score", type = Integer.class),
            @ColumnResult(name = "win_loss", type = String.class),
        }))
@Entity
public class GameSummary {
  @Id
  private final int idGame;
  private final Playlist playlist;
  private final boolean competitiveInd;
  private final Date date;
  private final String arena;
  private final int orangeScore;
  private final int blueScore;
  private final WinLoss winLoss;

  public GameSummary(int idGame, String playlist, boolean competitiveInd, Date date, String arena, int orangeScore, int blueScore, String winLoss) {
    this.idGame = idGame;
    this.playlist = Playlist.valueOf(playlist);
    this.competitiveInd = competitiveInd;
    this.date = date;
    this.arena = Arena.valueOf(arena).getNameForUi();
    this.orangeScore = orangeScore;
    this.blueScore = blueScore;
    this.winLoss = WinLoss.valueOf(winLoss);
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  public boolean isCompetitiveInd() {
    return competitiveInd;
  }

  public Date getDate() {
    return date;
  }

  public String getArena() {
    return arena;
  }

  public int getOrangeScore() {
    return orangeScore;
  }

  public int getBlueScore() {
    return blueScore;
  }

  public WinLoss getWinLoss() {
    return winLoss;
  }

  public int getIdGame() {
    return idGame;
  }
}
