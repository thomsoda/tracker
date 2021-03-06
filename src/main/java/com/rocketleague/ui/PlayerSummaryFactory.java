package com.rocketleague.ui;

import com.rocketleague.entity.TrackedPlayer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerSummaryFactory {

  public List<PlayerSummary> get(Iterable<TrackedPlayer> trackedPlayers) {
    List<PlayerSummary> playerSummaries = new ArrayList<>();
    for (TrackedPlayer trackedPlayer : trackedPlayers) {
      PlayerSummary playerSummary = new PlayerSummary.Builder()
          .idPlayer(trackedPlayer.getIdPlayer())
          .gamesPlayed(trackedPlayer.getPerformances().size())
          .goals(trackedPlayer.getTotalGoals())
          .assists(trackedPlayer.getTotalAssists())
          .saves(trackedPlayer.getTotalSaves())
          .averageGoals(trackedPlayer.getAverageGoals())
          .averageAssists(trackedPlayer.getAverageAssists())
          .averageSaves(trackedPlayer.getAverageSaves())
          .winPercentage(trackedPlayer.getWinPercentage())
          .build();
      playerSummaries.add(playerSummary);
    }
    return playerSummaries;
  }
}
