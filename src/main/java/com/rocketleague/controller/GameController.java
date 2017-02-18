package com.rocketleague.controller;

import com.rocketleague.entity.Performance;
import com.rocketleague.repository.PerformanceRepository;
import com.rocketleague.ui.GameSummaryCatalog;
import com.rocketleague.ui.GameSummaryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

  @Autowired
  private PerformanceRepository performanceRepository;

  @Autowired
  private GameSummaryFactory gameSummaryFactory;

  @RequestMapping(path = "/game-history")
  public GameSummaryCatalog gameHistory(@RequestParam(value = "playerId") String playerId) {
    List<Performance> performances = performanceRepository.findByPrimaryKeyIdPlayer(playerId);
    return new GameSummaryCatalog(gameSummaryFactory.get(performances));
  }

}
