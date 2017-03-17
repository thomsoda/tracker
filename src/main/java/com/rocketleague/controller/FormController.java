package com.rocketleague.controller;

import com.rocketleague.entity.TrackedPlayer;
import com.rocketleague.entity.mapped.AggregateGameStats;
import com.rocketleague.entity.mapped.GameStats;
import com.rocketleague.repository.FormRepository;
import com.rocketleague.repository.TrackedPlayerRepository;
import com.rocketleague.ui.AggregateGameStatsWrapper;
import com.rocketleague.ui.GameStatsWrapper;
import com.rocketleague.ui.TeamStatsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class FormController {
  @Autowired
  private FormRepository formRepository;
  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @RequestMapping(path = "/form")
  public GameStatsWrapper form(@RequestParam(value = "playerId") String playerId,
                                        @RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                        @RequestParam(value = "playlist", required = false) String playlist) {
    List<GameStats> gameStats = formRepository.findStatsForLastXGames(playerId, playlist, isRanked);
    return new GameStatsWrapper(gameStats);
  }

  @RequestMapping(path = "/aggregated-form")
  public AggregateGameStatsWrapper aggregatedForm(@RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                                  @RequestParam(value = "playlist", required = false) String playlist) {
    List<AggregateGameStats> aggregateGameStats = new ArrayList<>();
    for (TrackedPlayer trackedPlayer : trackedPlayerRepository.findAll()) {
      aggregateGameStats.addAll(formRepository.findAggregatedStatsForLastXGames(trackedPlayer.getIdPlayer(), playlist, isRanked));
    };
    Comparator<AggregateGameStats> byScore = Comparator.comparing(aggregate -> new Integer(aggregate.getScore()));
    aggregateGameStats.sort(byScore.reversed());
    return new AggregateGameStatsWrapper(aggregateGameStats);
  }

  @RequestMapping(path = "/team-form")
  public TeamStatsWrapper teamForm(@RequestParam(value = "playerId") String playerId,
                                   @RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                   @RequestParam(value = "playlist", required = false) String playlist) {
    return new TeamStatsWrapper(formRepository.findTeamStatsForLastXGames(playerId, playlist, isRanked));
  }

}
