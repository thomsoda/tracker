package com.rocketleague.repository;

import com.rocketleague.entity.mapped.PlayerContribution;

import java.util.List;

public interface ContributionRepositoryCustom {
  List<PlayerContribution> findScoreContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findGoalContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findAssistContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findSaveContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findShotContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findHatTrickContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findPlaymakerContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findSaviourContributions(String playlist, Boolean isRanked);
  List<PlayerContribution> findMvpContributions(String playlist, Boolean isRanked);
}
