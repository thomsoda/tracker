package com.rocketleague.controller;

import com.rocketleague.ui.stats.player.PlayerStats;
import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.PlayerSummaryCatalog;
import com.rocketleague.ui.PlayerSummaryFactory;
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
}
