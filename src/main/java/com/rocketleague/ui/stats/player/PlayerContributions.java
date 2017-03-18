package com.rocketleague.ui.stats.player;

import com.rocketleague.entity.mapped.PlayerContribution;

import java.util.List;

public class PlayerContributions {
  private List<PlayerContribution> contributions;

  public PlayerContributions(List<PlayerContribution> contributions) {
    this.contributions = contributions;
  }

  public List<PlayerContribution> getContributions() {
    return contributions;
  }
}
