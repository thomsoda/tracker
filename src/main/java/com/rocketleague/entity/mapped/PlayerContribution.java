package com.rocketleague.entity.mapped;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(name = "PlayerContributionMapping",
    classes = @ConstructorResult(
        targetClass = PlayerContribution.class,
        columns = {
            @ColumnResult(name = "id_player"),
            @ColumnResult(name = "total", type = Integer.class),
            @ColumnResult(name = "team_total", type = Integer.class),
            @ColumnResult(name = "contribution", type = BigDecimal.class)
        }))
@Entity
public class PlayerContribution {
  @Id
  private String playerId;
  private int total;
  private int teamTotal;
  private BigDecimal contribution;

  public PlayerContribution(String playerId, int total, int teamTotal, BigDecimal contribution) {
    this.playerId = playerId;
    this.total = total;
    this.teamTotal = teamTotal;
    this.contribution = contribution;
  }

  public String getPlayerId() {
    return playerId;
  }

  public int getTotal() {
    return total;
  }

  public int getTeamTotal() {
    return teamTotal;
  }

  public BigDecimal getContribution() {
    return contribution;
  }
}
