package com.rocketleague.ui.stats.player;

public class PlayerStats {
  private final PlayerTotals playerTotals;
  private final PlayerAverages playerAverages;
  private final PlayerContributionAverages playerContributionAverages;

  public PlayerStats(PlayerTotals playerTotals, PlayerAverages playerAverages, PlayerContributionAverages playerContributionAverages) {
    this.playerTotals = playerTotals;
    this.playerAverages = playerAverages;
    this.playerContributionAverages = playerContributionAverages;
  }

  public PlayerTotals getPlayerTotals() {
    return playerTotals;
  }

  public PlayerAverages getPlayerAverages() {
    return playerAverages;
  }

  public PlayerContributionAverages getPlayerContributionAverages() {
    return playerContributionAverages;
  }
}
