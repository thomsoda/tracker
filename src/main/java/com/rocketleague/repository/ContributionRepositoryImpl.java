package com.rocketleague.repository;

import com.rocketleague.entity.mapped.PlayerContribution;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ContributionRepositoryImpl implements ContributionRepositoryCustom {
  public static final String SELECT_CLAUSE = "select all_p.id_player, p.{columnAlias} as total, all_p.{columnAlias} as team_total, ifnull((p.{columnAlias} / all_p.{columnAlias} * 100), 0) as contribution from ";
  public static final String SUB_SELECT_CLAUSE = "select p.id_player, sum({tableAlias}.{column}) as {columnAlias} ";
  public static final String FROM_CLAUSE = "from performance p join tracked_player tp on tp.id_player = p.id_player join game g on g.id_game = p.id_game ";
  public static final String JOIN_CLAUSE = "join performance all_p on p.id_game = all_p.id_game and p.team = all_p.team ";
  public static final String WHERE_CLAUSE = "where g.forfeit_ind = 0 ";
  public static final String GROUP_BY_CLAUSE = "group by p.id_player ";
  public static final String ORDER_BY_CLAUSE = "order by p.{columnAlias}/all_p.{columnAlias} desc, p.id_player;";

  @PersistenceContext
  private EntityManager entityManager;

  private Query getQuery(String playlist, Boolean isRanked, String column, String columnAlias) {
    String queryString =  SELECT_CLAUSE.replace("{columnAlias}", columnAlias) + "(" +
        getSubQuery(playlist, isRanked, true, column, columnAlias) + ") all_p join (" +
        getSubQuery(playlist, isRanked, false, column, columnAlias) + ") p on p.id_player = all_p.id_player "
        + ORDER_BY_CLAUSE.replace("{columnAlias}", columnAlias);
    return entityManager.createNativeQuery(queryString, "PlayerContributionMapping");
  }

  private String getSubQuery(String playlist, Boolean isRanked, Boolean isTeamTotal, String column, String columnAlias) {
    return SUB_SELECT_CLAUSE.replace("{tableAlias}", isTeamTotal? "all_p" : "p").replace("{column}", column).
        replace("{columnAlias}", columnAlias) + FROM_CLAUSE + (isTeamTotal? JOIN_CLAUSE : "") + getWhereClause(playlist, isRanked) +
        GROUP_BY_CLAUSE;
  }

  private String getWhereClause(String playlist, Boolean isRanked) {
    String modifiedWhereClause = WHERE_CLAUSE;
    if (playlist != null) {
      modifiedWhereClause += ("and g.playlist = '" + playlist + "' ");
    }
    if (isRanked != null) {
      modifiedWhereClause += ("and g.competitive_ind = " + (isRanked ? "1" : "0") + " ");
    }
    return modifiedWhereClause;
  }

  @Override
  public List<PlayerContribution> findScoreContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "score", "score");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findGoalContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "goals", "goals");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findAssistContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "assists", "assists");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findSaveContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "saves", "saves");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findShotContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "shots", "shots");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findHatTrickContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "goals div 3", "hat_tricks");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findPlaymakerContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "assists div 3", "playmakers");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findSaviourContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "saves div 3", "saviours");
    return query.getResultList();
  }

  @Override
  public List<PlayerContribution> findMvpContributions(String playlist, Boolean isRanked) {
    Query query = getQuery(playlist, isRanked, "mvp_ind", "mvp_ind");
    return query.getResultList();
  }
}
