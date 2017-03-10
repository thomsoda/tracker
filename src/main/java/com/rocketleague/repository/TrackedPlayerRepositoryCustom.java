package com.rocketleague.repository;

import java.util.List;

public interface TrackedPlayerRepositoryCustom {

  List<PlayerAverage> findScoreAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findGoalAverages(String playlist, Boolean isRanked);
  List<PlayerAverage> findAssistAverages(String playlist, Boolean isRanked);

  List<PlayerAverage> findSaveAverages(String playlist, Boolean isRanked);

  List<PlayerAverage> findShotAverages(String playlist, Boolean isRanked);
}
