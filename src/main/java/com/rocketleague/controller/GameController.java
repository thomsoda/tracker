package com.rocketleague.controller;

import com.rocketleague.entity.Game;
import com.rocketleague.entity.Performance;
import com.rocketleague.refdata.Team;
import com.rocketleague.repository.GameRepository;
import com.rocketleague.repository.PerformanceRepository;
import com.rocketleague.ui.GameDetail;
import com.rocketleague.ui.GamePerformanceFactory;
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
  private GameRepository gameRepository;

  @Autowired
  private GameSummaryFactory gameSummaryFactory;

  @Autowired
  private GamePerformanceFactory gamePerformanceFactory;

  @RequestMapping(path = "/game-history")
  public GameSummaryCatalog gameHistory(@RequestParam(value = "playerId") String playerId) {
    List<Performance> performances = performanceRepository.findByPrimaryKeyIdPlayerOrderByPrimaryKeyGameDtPlayedDesc(playerId);
    return new GameSummaryCatalog(gameSummaryFactory.get(performances));
  }

  @RequestMapping(path = "/game-detail")
  public GameDetail gameDetail(@RequestParam(value = "gameId") int idGame) {
    List<Performance> bluePerformances = performanceRepository.findByPrimaryKeyGameIdGameAndTeamOrderByScoreDesc(idGame, Team.BLUE);
    List<Performance> orangePerformances = performanceRepository.findByPrimaryKeyGameIdGameAndTeamOrderByScoreDesc(idGame, Team.ORANGE);

    Game game = gameRepository.findOne(idGame);

    return new GameDetail(gamePerformanceFactory.get(bluePerformances), gamePerformanceFactory.get(orangePerformances), game);
  }


}
