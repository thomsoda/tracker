package com.rocketleague.repository;

import java.util.List;

public interface TrackedPlayerRepositoryCustom {

  List<PlayerAverage> findScoreAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findGoalAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findAssistAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findSaveAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findShotAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findHatTrickAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findPlaymakerAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findSaviourAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findMvpAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findTeamMvpAverages(String playlist, Boolean isRanked);
}
