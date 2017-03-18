package com.rocketleague.controller;

import com.rocketleague.repository.ContributionRepository;
import com.rocketleague.ui.stats.player.PlayerContributions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tracked-players")
public class ContributionController {

  @Autowired
  private ContributionRepository contributionRepository;

  @GetMapping(path = "/goal-contributions")
  public PlayerContributions getGoalContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked,
                                        @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findGoalContributions(playlist, isRanked));
  }

  @GetMapping(path = "/score-contributions")
  public PlayerContributions getScoreContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findScoreContributions(playlist, isRanked));
  }

  @GetMapping(path = "/assist-contributions")
  public PlayerContributions getAssistContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findAssistContributions(playlist, isRanked));
  }

  @GetMapping(path = "/save-contributions")
  public PlayerContributions getSaveContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findSaveContributions(playlist, isRanked));
  }

  @GetMapping(path = "/shot-contributions")
  public PlayerContributions getShotContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findShotContributions(playlist, isRanked));
  }

  @GetMapping(path = "/hattrick-contributions")
  public PlayerContributions getHatTrickContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findHatTrickContributions(playlist, isRanked));
  }

  @GetMapping(path = "/playmaker-contributions")
  public PlayerContributions getPlaymakerContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findPlaymakerContributions(playlist, isRanked));
  }

  @GetMapping(path = "/saviour-contributions")
  public PlayerContributions getSaviourContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findSaviourContributions(playlist, isRanked));
  }

  @GetMapping(path = "/mvp-contributions")
  public PlayerContributions getMvpContributions(@RequestParam(value = "isRanked", required = false) Boolean isRanked, @RequestParam(value = "playlist", required = false) String playlist) {
    return new PlayerContributions(contributionRepository.findMvpContributions(playlist, isRanked));
  }

}
