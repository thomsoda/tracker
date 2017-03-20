package com.rocketleague.ui;

import com.rocketleague.entity.mapped.PlayerMost;

import java.util.List;

public class PlayerMostWrapper {

  private final List<PlayerMost> playerMosts;

  public PlayerMostWrapper(List<PlayerMost> playerMosts) {
    this.playerMosts = playerMosts;
  }

  public List<PlayerMost> getPlayerMosts() {
    return playerMosts;
  }
}
