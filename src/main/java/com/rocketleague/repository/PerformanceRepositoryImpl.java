package com.rocketleague.repository;

import com.rocketleague.entity.mapped.AggregateGameStats;
import com.rocketleague.entity.mapped.GameStats;
import com.rocketleague.entity.mapped.GameSummary;
import com.rocketleague.entity.mapped.TeamStats;

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

  private static final String FORM_SELECT_CLAUSE = "select p.id_player as id_player, g.dt_played as dt_played, p.score as score, p.goals as goals, p.assists as assists, p.saves as saves, p.shots as shots, (p.goals div 3) as hat_tricks, (p.assists div 3) as playmakers, (p.saves div 3) as saviours, p.mvp_ind as mvp_ind, (case when vtm.id_player is null then 0 else 1 end) as team_mvp_ind ";
  private static final String FORM_FROM_CLAUSE = "from performance p join game g on g.id_game = p.id_game and g.forfeit_ind = 0 ";
  private static final String FORM_TEAM_MVP_JOIN_CLAUSE = "left join v_team_mvp vtm on vtm.id_player = p.id_player and vtm.id_game = p.id_game ";
  private static final String FORM_WHERE_CLAUSE = "where p.id_player = '{playerId}' ";
  private static final String FORM_ORDER_BY_CLAUSE = "order by g.dt_played desc ";
  private static final String FORM_LIMIT_CLAUSE = "limit 10";

  private static final String AGGREGATE_SELECT_CLAUSE = "select a.id_player, count(1) as games, sum(a.score) as score, sum(a.goals) as goals, sum(a.assists) as assists, sum(a.saves) as saves, sum(a.shots) as shots, sum(a.hat_tricks) as hat_tricks, sum(a.playmakers) as playmakers, sum(a.saviours) as saviours, sum(a.mvp_ind = 1) as mvps, sum(a.team_mvp_ind = 1) as team_mvps ";
  private static final String AGGREGATE_FROM_CLAUSE = "from ({lastXQuery}) a group by a.id_player";

  @Override
  public List<AggregateGameStats> findAggregatedStatsForLastXGames(String playerId, String playlist, Boolean isRanked) {
    Query query = entityManager.createNativeQuery(AGGREGATE_SELECT_CLAUSE + AGGREGATE_FROM_CLAUSE.replace("{lastXQuery}", getQuery(playerId, playlist, isRanked)), "AggregateGameStatsMapping");
    return query.getResultList();
  }

  @Override
  public List<GameStats> findStatsForLastXGames(String playerId, String playlist, Boolean isRanked) {
    Query query = entityManager.createNativeQuery(getQuery(playerId, playlist, isRanked), "GameStatsMapping");
    return query.getResultList();
  }

  @Override
  public TeamStats findTeamStatsForLastXGames(String playerId, String playlist, Boolean isRanked) {
    Query query = entityManager.createNativeQuery("select * from performance", TeamStats.class);
    return (TeamStats)query.getSingleResult();
  }

  private String getQuery(String playerId, String playlist, Boolean isRanked) {
    return FORM_SELECT_CLAUSE + FORM_FROM_CLAUSE + FORM_TEAM_MVP_JOIN_CLAUSE + getWhereClause(FORM_WHERE_CLAUSE, playerId, playlist, isRanked) + FORM_ORDER_BY_CLAUSE + FORM_LIMIT_CLAUSE;
  }

}
