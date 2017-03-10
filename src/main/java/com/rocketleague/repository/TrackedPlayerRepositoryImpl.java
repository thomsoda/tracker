package com.rocketleague.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class TrackedPlayerRepositoryImpl implements TrackedPlayerRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;
  public static final String SELECT_CLAUSE = "select p.id_player, count(1) as games, sum(p.{column}) as total, avg(p.{column}) as average ";
  public static final String FROM_CLAUSE = "from performance p join tracked_player tp on tp.id_player = p.id_player join game g on g.id_game = p.id_game ";
  public static final String WHERE_CLAUSE = "where g.forfeit_ind = 0 ";
  public static final String GROUP_BY_CLAUSE = "group by p.id_player ";
  public static final String ORDER_BY_CLAUSE = "order by avg(p.{column}) desc, p.id_player;";

  @Override
  public List<PlayerAverage> findScoreAverages(String playlist, Boolean isRanked) {
    Query query = getQuery("score", playlist, isRanked);
    return query.getResultList();
  }

  @Override
  public List<PlayerAverage> findGoalAverages(String playlist, Boolean isRanked) {
    Query query = getQuery("goals", playlist, isRanked);
    return query.getResultList();
  }

  @Override
  public List<PlayerAverage> findAssistAverages(String playlist, Boolean isRanked) {
    Query query = getQuery("assists", playlist, isRanked);
    return query.getResultList();
  }

  @Override
  public List<PlayerAverage> findSaveAverages(String playlist, Boolean isRanked) {
    Query query = getQuery("saves", playlist, isRanked);
    return query.getResultList();
  }

  @Override
  public List<PlayerAverage> findShotAverages(String playlist, Boolean isRanked) {
    Query query = getQuery("shots", playlist, isRanked);
    return query.getResultList();
  }

  private Query getQuery(String column, String playlist, Boolean isRanked) {
    String selectClause = getSelectClause(column);
    String whereClause = getWhereClause(playlist, isRanked);
    String orderByClause = getOrderByClause(column);
    return entityManager.createNativeQuery(selectClause + FROM_CLAUSE + whereClause + GROUP_BY_CLAUSE +
        orderByClause, "PlayerAverageMapping");
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

  private String getOrderByClause(String column) {
    return ORDER_BY_CLAUSE.replace("{column}", column);
  }

  private String getSelectClause(String column) {
    return SELECT_CLAUSE.replace("{column}", column);
  }
}
