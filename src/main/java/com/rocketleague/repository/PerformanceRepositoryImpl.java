package com.rocketleague.repository;

import com.rocketleague.entity.mapped.GameSummary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PerformanceRepositoryImpl implements PerformanceRepositoryCustom {

  public static final String SELECT_CLAUSE =
      "select g.id_game, g.playlist, g.competitive_ind, g.dt_played, g.arena, os.score as orange_score, bs.score as blue_score, case (g.winning_team = p.team) when true then 'WIN' else 'LOSS' end as win_loss ";
  public static final String FROM_CLAUSE =
      "from tracked_player tp join performance p on p.id_player = tp.id_player join game g on g.id_game = p.id_game join score os on os.id_game = g.id_game and os.team = 'ORANGE' join score bs on bs.id_game = g.id_game and bs.team = 'BLUE' ";
  public static final String WHERE_CLAUSE = "where tp.id_player = '{playerId}' ";
  public static final String ORDER_BY_CLAUSE = "order by dt_played desc;";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<GameSummary> findGameSummaries(String playerId, String playlist, Boolean isRanked) {
    String whereClause = getWhereClause(WHERE_CLAUSE, playerId, playlist, isRanked);
    Query query = entityManager.createNativeQuery(SELECT_CLAUSE + FROM_CLAUSE + whereClause + ORDER_BY_CLAUSE,
        "GameSummaryMapping");
    return query.getResultList();
  }

  private String getWhereClause(String whereClause, String playerId, String playlist, Boolean isRanked) {
    String whereClauseToReturn = whereClause.replace("{playerId}", playerId);
    if (playlist != null) {
      whereClauseToReturn += ("and g.playlist = '" + playlist + "' ");
    }
    if (isRanked != null) {
      whereClauseToReturn += ("and g.competitive_ind = " + (isRanked ? "1" : "0") + " ");
    }
    return whereClauseToReturn;
  }

}
