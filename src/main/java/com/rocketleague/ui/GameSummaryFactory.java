package com.rocketleague.ui;

import com.rocketleague.entity.Game;
import com.rocketleague.entity.Performance;
import com.rocketleague.refdata.WinLoss;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GameSummaryFactory {

  public Set<GameSummary> get(Iterable<Performance> performances) {
    Set<GameSummary> gameSummaries = new HashSet<>();
    for (Performance performance : performances) {
      Game game = performance.getGame();
      GameSummary gameSummary = new GameSummary.Builder()
          .playlist(game.getPlaylist())
          .competitiveInd(game.isCompetitiveInd())
          .date(game.getDtPlayed())
          .arena(game.getArena())
          .orangeScore(game.getOrangeScore())
          .blueScore(game.getBlueScore())
          .winLoss(performance.inWinningTeam()? WinLoss.WIN : WinLoss.LOSS)
          .build();
      gameSummaries.add(gameSummary);
    }
    return gameSummaries;
  }
}
