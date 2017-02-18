package com.rocketleague.controller;

import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.PlayerSummaryCatalog;
import com.rocketleague.ui.PlayerSummaryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tracked-players")
public class PlayerController {

  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @Autowired
  private PlayerSummaryFactory playerSummaryFactory;

  @GetMapping(path = "/all")
  public PlayerSummaryCatalog getAllTrackedPlayers() {
    Iterable<TrackedPlayer> trackedPlayers = trackedPlayerRepository.findAllByOrderByIdPlayer();
    return new PlayerSummaryCatalog(playerSummaryFactory.get(trackedPlayers));
  }
}
