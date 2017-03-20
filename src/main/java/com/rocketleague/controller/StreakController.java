package com.rocketleague.controller;

import com.rocketleague.controller.streak.*;
import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.entity.mapped.PerformanceStats;
import com.rocketleague.repository.PerformanceRepository;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.PlayerStreak;
import com.rocketleague.ui.PlayerStreakWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/streak")
public class StreakController {
  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @Autowired
  private PerformanceRepository performanceRepository;

  @RequestMapping(path = "/longest-goal")
  public PlayerStreakWrapper getLongestGoalStreak(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerStreak> longestGoalStreaks = getPlayerStreaks(isRanked, playlist, new GoalStreakIdentifier());
    return new PlayerStreakWrapper(longestGoalStreaks);
  }

  @RequestMapping(path = "/longest-drought")
  public PlayerStreakWrapper getLongestGoalDrought(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerStreak> longestGoalDroughts = getPlayerStreaks(isRanked, playlist, new DroughtStreakIdentifier());
    return new PlayerStreakWrapper(longestGoalDroughts);
  }

  @RequestMapping(path = "/longest-winning")
  public PlayerStreakWrapper getLongestWinningStreak(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerStreak> longestWinningStreaks = getPlayerStreaks(isRanked, playlist, new WinningStreakIdentifier());
    return new PlayerStreakWrapper(longestWinningStreaks);
  }

  @RequestMapping(path = "/longest-losing")
  public PlayerStreakWrapper getLongestLosingStreak(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerStreak> longestLosingStreaks = getPlayerStreaks(isRanked, playlist, new LosingStreakIdentifier());
    return new PlayerStreakWrapper(longestLosingStreaks);
  }


  private List<PlayerStreak> getPlayerStreaks(Boolean isRanked, String playlist, StreakIdentifier streakIdentifier) {
    List<PlayerStreak> longestStreaks = new ArrayList<>();
    for (TrackedPlayer trackedPlayer : trackedPlayerRepository.findAll()) {
      List<PerformanceStats> performances = performanceRepository.findPerformances(trackedPlayer.getIdPlayer(), playlist, isRanked);
      if (!performances.isEmpty()) {
        longestStreaks.add(getLongestStreak(trackedPlayer.getIdPlayer(), performances, streakIdentifier));
      }
    }
    Comparator<PlayerStreak> byNumberOfGames = Comparator.comparing(streak -> streak.getNumberOfGames());
    longestStreaks.sort(byNumberOfGames.reversed());
    return longestStreaks;
  }

  private PlayerStreak getLongestStreak(String playerId, List<PerformanceStats> performances, StreakIdentifier streakIdentifier) {
    List<PlayerStreak> streaks = getStreaks(performances, streakIdentifier);
    return getMaxStreak(playerId, streaks);
  }

  private List<PlayerStreak> getStreaks(List<PerformanceStats> performances, StreakIdentifier streakIdentifier) {
    boolean onStreak = false;
    PlayerStreak currentStreak = null;

    List<PlayerStreak> scoringStreaks = new ArrayList<>();
    for (PerformanceStats performance : performances) {
      if (streakIdentifier.isPartOfStreak(performance) && !onStreak) {
        currentStreak = new PlayerStreak(performance.getIdPlayer(), 1, performance.getDtPlayed(), null);
        onStreak = true;
      }
      else if (streakIdentifier.isPartOfStreak(performance) && onStreak) {
        currentStreak.addGameToStreak();
      }
      else if (!streakIdentifier.isPartOfStreak(performance) && onStreak) {
        currentStreak.endStreak(performance.getDtPlayed());
        scoringStreaks.add(currentStreak);
        onStreak = false;
      }
    }

    if (currentStreak != null && currentStreak.getDtEnded() == null) {
      scoringStreaks.add(currentStreak);
    }
    return scoringStreaks;

  }

  private PlayerStreak getMaxStreak(String playerId, List<PlayerStreak> streaks) {
    PlayerStreak maxStreak = new PlayerStreak(playerId, 0, null, null);
    for (PlayerStreak goalDrought : streaks) {
      if (goalDrought.getNumberOfGames() > maxStreak.getNumberOfGames()) {
        maxStreak = goalDrought;
      }
    }
    return maxStreak;
  }

}
