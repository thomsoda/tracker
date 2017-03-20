package com.rocketleague.ui;

import java.util.Date;

public class PlayerStreak {

  private final String playerId;
  private int numberOfGames;
  private final Date dtStarted;
  private Date dtEnded;

  public PlayerStreak(String playerId, int numberOfGames, Date dtStarted, Date dtEnded) {
    this.playerId = playerId;
    this.numberOfGames = numberOfGames;
    this.dtStarted = dtStarted;
    this.dtEnded = dtEnded;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getNumberOfGames() {
    return numberOfGames;
  }

  public Date getDtStarted() {
    return dtStarted;
  }

  public Date getDtEnded() {
    return dtEnded;
  }

  public void addGameToStreak() {
    this.numberOfGames++;
  }

  public void endStreak(Date dtEnded) {
    this.dtEnded = dtEnded;
  }
}
