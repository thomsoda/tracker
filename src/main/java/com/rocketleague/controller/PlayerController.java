package com.rocketleague.controller;

import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.PlayerSummaryCatalog;
import com.rocketleague.ui.PlayerSummaryFactory;
import com.rocketleague.ui.stats.player.PlayerAverages;
import com.rocketleague.ui.stats.player.PlayerStats;
import com.rocketleague.ui.stats.player.PlayerStatsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tracked-players")
public class PlayerController {

  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @Autowired
  private PlayerSummaryFactory playerSummaryFactory;
  @Autowired
  private PlayerStatsFactory playerStatsFactory;

  @GetMapping(path = "/all")
  public PlayerSummaryCatalog getAllTrackedPlayers() {
    Iterable<TrackedPlayer> trackedPlayers = trackedPlayerRepository.findAllByOrderByIdPlayer();
    return new PlayerSummaryCatalog(playerSummaryFactory.get(trackedPlayers));
  }

  @GetMapping(path = "/stats")
  public PlayerStats getPlayerStats(@RequestParam(value = "idPlayer") String idPlayer) {
    TrackedPlayer trackedPlayer = trackedPlayerRepository.findOne(idPlayer);
    return playerStatsFactory.get(trackedPlayer);
  }

  @GetMapping(path = "/goal-averages")
  public PlayerAverages getGoalAverages(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerAverages(trackedPlayerRepository.findGoalAverages(playlist, isRanked));
  }

  @GetMapping(path = "/score-averages")
  public PlayerAverages getScoreAverages(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerAverages(trackedPlayerRepository.findScoreAverages(playlist, isRanked));
  }

  @GetMapping(path = "/assist-averages")
  public PlayerAverages getAssistAverages(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerAverages(trackedPlayerRepository.findAssistAverages(playlist, isRanked));
  }

  @GetMapping(path = "/save-averages")
  public PlayerAverages getSaveAverages(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerAverages(trackedPlayerRepository.findSaveAverages(playlist, isRanked));
  }

  @GetMapping(path = "/shot-averages")
  public PlayerAverages getShotAverages(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerAverages(trackedPlayerRepository.findShotAverages(playlist, isRanked));
  }


}
