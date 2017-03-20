package com.rocketleague.controller;

import com.rocketleague.controller.most.*;
import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.entity.mapped.PerformanceStats;
import com.rocketleague.entity.mapped.PlayerMost;
import com.rocketleague.repository.PerformanceRepository;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.PlayerMostWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/most-x-in-a-game")
public class MostController {
  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @Autowired
  private PerformanceRepository performanceRepository;

  @GetMapping(path = "/goals")
  public PlayerMostWrapper getMostGoalsInAGame(@RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                               @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerMost> playerMosts = getPlayerMosts(isRanked, playlist, new GoalsGetter());
    return new PlayerMostWrapper(playerMosts);
  }

  private List<PlayerMost> getPlayerMosts(Boolean isRanked, String playlist, StatGetter statGetter) {
    List<PlayerMost> playerMosts = new ArrayList<>();
    for (TrackedPlayer trackedPlayer : trackedPlayerRepository.findAll()) {
      List<PerformanceStats> performances = performanceRepository.findPerformances(trackedPlayer.getIdPlayer(), playlist, isRanked);
      if (!performances.isEmpty()) {
        playerMosts.add(getPlayerMosts(trackedPlayer.getIdPlayer(), performances, statGetter));
      }
    }
    Comparator<PlayerMost> byTotal = Comparator.comparing(playerMost -> playerMost.getTotal());
    playerMosts.sort(byTotal.reversed());
    return playerMosts;
  }

  private PlayerMost getPlayerMosts(String playerId, List<PerformanceStats> performances, StatGetter statGetter) {
    PlayerMost playerMost = new PlayerMost(playerId, -1, null, null);
    for (PerformanceStats performance : performances) {
      if (statGetter.getTotal(performance) > playerMost.getTotal()) {
        playerMost.setNewMax(statGetter.getTotal(performance), performance.getGameId(), performance.getDtPlayed());
      }
    }
    return playerMost;
  }

  @GetMapping(path = "/assists")
  public PlayerMostWrapper getMostAssistsInAGame(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerMost> playerMosts = getPlayerMosts(isRanked, playlist, new AssistsGetter());
    return new PlayerMostWrapper(playerMosts);
  }

  @GetMapping(path = "/saves")
  public PlayerMostWrapper getMostSavesInAGame(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerMost> playerMosts = getPlayerMosts(isRanked, playlist, new SavesGetter());
    return new PlayerMostWrapper(playerMosts);
  }

  @GetMapping(path = "/shots")
  public PlayerMostWrapper getMostShotsInAGame(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerMost> playerMosts = getPlayerMosts(isRanked, playlist, new ShotsGetter());
    return new PlayerMostWrapper(playerMosts);
  }

  @GetMapping(path = "/score")
  public PlayerMostWrapper getMostPointsInAGame(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    List<PlayerMost> playerMosts = getPlayerMosts(isRanked, playlist, new ScoreGetter());
    return new PlayerMostWrapper(playerMosts);
  }

}
