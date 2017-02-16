package com.rocketleague;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Set;

@Entity
public class TrackedPlayer {
  @Id
  private String idPlayer;
  private Date dtTrackingStarted;

  @OneToMany(mappedBy = "primaryKey.idPlayer")
  private Set<Performance> performances;

  public String getIdPlayer() {
    return idPlayer;
  }

  public Date getDtTrackingStarted() {
    return dtTrackingStarted;
  }

  public void setIdPlayer(String idPlayer) {
    this.idPlayer = idPlayer;
  }

  public void setDtTrackingStarted(Date dtTrackingStarted) {
    this.dtTrackingStarted = dtTrackingStarted;
  }

  public Set<Performance> getPerformances() {
    return performances;
  }

  public void setPerformances(Set<Performance> performances) {
    this.performances = performances;
  }

  public int getTotalGoals() {
    int totalGoals = 0;
    for (Performance performance : performances) {
      totalGoals += performance.getGoals();
    }
    return totalGoals;
  }

  public int getTotalAssists() {
    int totalAssists = 0;
    for (Performance performance : performances) {
      totalAssists += performance.getAssists();
    }
    return totalAssists;
  }

  public int getTotalSaves() {
    int totalSaves = 0;
    for (Performance performance : performances) {
      totalSaves += performance.getSaves();
    }
    return totalSaves;
  }

  public int getTotalWins() {
    int totalWins = 0;
    for (Performance performance : performances) {
      if (performance.inWinningTeam()) {
        totalWins++;
      }
    }
    return totalWins;
  }

  public BigDecimal getAverageGoals() {
    if (performances.size() == 0) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(getTotalGoals()).divide(new BigDecimal(performances.size()), 2, RoundingMode.HALF_UP);
  }

  public BigDecimal getAverageAssists() {
    if (performances.size() == 0) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(getTotalAssists()).divide(new BigDecimal(performances.size()), 2, RoundingMode.HALF_UP);
  }

  public BigDecimal getAverageSaves() {
    if (performances.size() == 0) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(getTotalSaves()).divide(new BigDecimal(performances.size()), 2, RoundingMode.HALF_UP);
  }

  public BigDecimal getWinPercentage() {
    if (performances.size() == 0) {
      return BigDecimal.ZERO;
    }
    return new BigDecimal(getTotalWins()).divide(new BigDecimal(performances.size()), 4, RoundingMode.HALF_UP)
        .multiply(new BigDecimal(100)).setScale(2);
  }

}
