package com.rocketleague.repository;

public class PlayerAverageFilter {
  private String playlist;
  private Boolean isRanked;

  public PlayerAverageFilter(String playlist, Boolean isRanked) {
    this.playlist = playlist;
    this.isRanked = isRanked;
  }

  public String getPlaylist() {
    return playlist;
  }

  public Boolean getRanked() {
    return isRanked;
  }
}
