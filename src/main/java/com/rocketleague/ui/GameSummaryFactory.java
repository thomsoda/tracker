package com.rocketleague.ui;

import com.rocketleague.entity.Game;
import com.rocketleague.entity.Performance;
import com.rocketleague.refdata.WinLoss;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameSummaryFactory {

  public List<GameSummary> get(Iterable<Performance> performances) {
    List<GameSummary> gameSummaries = new ArrayList<>();
    for (Performance performance : performances) {
      Game game = performance.getGame();
      GameSummary gameSummary = new GameSummary.Builder()
          .idGame(game.getIdGame())
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
