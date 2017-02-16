package com.rocketleague;

import com.rocketleague.ui.PlayerSummary;
import com.rocketleague.ui.PlayerSummaryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping(path = "/tracked-players")
public class MainController {

  @Autowired
  private TrackedPlayerRepository trackedPlayerRepository;

  @Autowired
  private PlayerSummaryFactory playerSummaryFactory;

  @GetMapping(path = "/all")
  public
  @ResponseBody
  Iterable<PlayerSummary> getAllTrackedPlayers() {
    Iterable<TrackedPlayer> trackedPlayers = trackedPlayerRepository.findAll();
    return playerSummaryFactory.get(trackedPlayers);
  }
}
