package com.rocketleague.controller;

import com.rocketleague.repository.GameRepository;
import com.rocketleague.ui.ArenaWinStatsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArenaController {

  @Autowired
  private GameRepository gameRepository;

  @GetMapping(path = "/arena-win-percents")
  public ArenaWinStatsWrapper getArenaWinPercents(@RequestParam(value = "playerId") String playerId,
                                                  @RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                                  @RequestParam(value = "playlist", required = false) String playlist) {
    return new ArenaWinStatsWrapper(gameRepository.findArenaWinPercents(playerId, playlist, isRanked));
  }
}
