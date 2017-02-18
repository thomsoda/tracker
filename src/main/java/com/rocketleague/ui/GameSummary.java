package com.rocketleague.ui;

import com.rocketleague.refdata.Arena;
import com.rocketleague.refdata.Playlist;
import com.rocketleague.refdata.WinLoss;

import java.util.Date;

public class GameSummary {
  private final int idGame;
  private final Playlist playlist;
  private final boolean competitiveInd;
  private final Date date;
  private final Arena arena;
  private final int orangeScore;
  private final int blueScore;
  private final WinLoss winLoss;

  private GameSummary(Builder builder) {
    idGame = builder.idGame;
    playlist = builder.playlist;
    competitiveInd = builder.competitiveInd;
    date = builder.date;
    arena = builder.arena;
    orangeScore = builder.orangeScore;
    blueScore = builder.blueScore;
    winLoss = builder.winLoss;
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

  public Arena getArena() {
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

  public static class Builder {
    private int idGame;
    private Playlist playlist;
    private boolean competitiveInd;
    private Date date;
    private Arena arena;
    private int orangeScore;
    private int blueScore;
    private WinLoss winLoss;

    public Builder idGame(int idGame) {
      this.idGame = idGame;
      return this;
    }

    public Builder playlist(Playlist playlist) {
      this.playlist = playlist;
      return this;
    }

    public Builder competitiveInd(boolean competitiveInd) {
      this.competitiveInd = competitiveInd;
      return this;
    }

    public Builder date(Date date) {
      this.date = date;
      return this;
    }

    public Builder arena(Arena arena) {
      this.arena = arena;
      return this;
    }

    public Builder orangeScore(int orangeScore) {
      this.orangeScore = orangeScore;
      return this;
    }

    public Builder blueScore(int blueScore) {
      this.blueScore = blueScore;
      return this;
    }

    public Builder winLoss(WinLoss winLoss) {
      this.winLoss = winLoss;
      return this;
    }

    public GameSummary build() {
      return new GameSummary(this);
    }
  }
}
