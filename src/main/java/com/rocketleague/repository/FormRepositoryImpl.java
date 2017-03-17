package com.rocketleague.repository;

import com.rocketleague.entity.mapped.AggregateGameStats;
import com.rocketleague.entity.mapped.GameStats;
import com.rocketleague.entity.mapped.TeamStats;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class FormRepositoryImpl implements FormRepositoryCustom {
  private static final String FORM_SELECT_CLAUSE = "select p.id_player as id_player, g.id_game as id_game, g.dt_played as dt_played, p.score as score, p.goals as goals, p.assists as assists, p.saves as saves, p.shots as shots, (p.goals div 3) as hat_tricks, (p.assists div 3) as playmakers, (p.saves div 3) as saviours, p.mvp_ind as mvp_ind, (case when vtm.id_player is null then 0 else 1 end) as team_mvp_ind ";
  private static final String FROM_CLAUSE = "from performance p join game g on g.id_game = p.id_game and g.forfeit_ind = 0 ";
  private static final String FORM_TEAM_MVP_JOIN_CLAUSE = "left join v_team_mvp vtm on vtm.id_player = p.id_player and vtm.id_game = p.id_game ";
  private static final String WHERE_CLAUSE = "where p.id_player = '{playerId}' ";
  private static final String ORDER_BY_CLAUSE = "order by g.dt_played desc ";
  private static final String LIMIT_CLAUSE = "limit 10";

  private static final String AGGREGATE_SELECT_CLAUSE = "select a.id_player, count(1) as games, sum(a.score) as score, sum(a.goals) as goals, sum(a.assists) as assists, sum(a.saves) as saves, sum(a.shots) as shots, sum(a.hat_tricks) as hat_tricks, sum(a.playmakers) as playmakers, sum(a.saviours) as saviours, sum(a.mvp_ind = 1) as mvps, sum(a.team_mvp_ind = 1) as team_mvps ";
  private static final String AGGREGATE_FROM_CLAUSE = "from ({lastXQuery}) a group by a.id_player";

  private static final String TEAM_SELECT_CLAUSE = "select case when p.team = g.winning_team then 'WIN' else 'LOSS' end as win_loss, gf.score as goals_for, ga.score as goals_against ";
  private static final String JOIN_GF_CLAUSE = "join score gf on gf.id_game = g.id_game and gf.team = p.team ";
  private static final String JOIN_GA_CLAUSE = "join score ga on ga.id_game = g.id_game and ga.team != p.team ";


  @PersistenceContext
  private EntityManager entityManager;

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
  public List<TeamStats> findTeamStatsForLastXGames(String playerId, String playlist, Boolean isRanked) {
    Query query = entityManager.createNativeQuery(getTeamStatsQuery(playerId, playlist, isRanked), "TeamStatsMapping");
    return query.getResultList();
  }

  private String getQuery(String playerId, String playlist, Boolean isRanked) {
    return FORM_SELECT_CLAUSE + FROM_CLAUSE + FORM_TEAM_MVP_JOIN_CLAUSE + getWhereClause(playerId, playlist, isRanked) + ORDER_BY_CLAUSE + LIMIT_CLAUSE;
  }

  private String getWhereClause(String playerId, String playlist, Boolean isRanked) {
    String whereClauseToReturn = WHERE_CLAUSE.replace("{playerId}", playerId);
    if (playlist != null) {
      whereClauseToReturn += ("and g.playlist = '" + playlist + "' ");
    }
    if (isRanked != null) {
      whereClauseToReturn += ("and g.competitive_ind = " + (isRanked ? "1" : "0") + " ");
    }
    return whereClauseToReturn;
  }

  private String getTeamStatsQuery(String playerId, String playlist, Boolean isRanked) {
    return "select '" + playerId + "' as id_player, count(1) as games, sum(g.win_loss = 'WIN') as won, sum(g.win_loss = 'LOSS') as lost, sum(g.goals_for) as goals_for, sum(g.goals_against) as goals_against, " +
        "sum(g.goals_for) - sum(g.goals_against) as goal_difference, sum(g.goals_against = 0) as clean_sheets " +
        "from (" + getTeamSelectSubQuery(playerId, playlist, isRanked) + ") g";
  }

  private String getTeamSelectSubQuery(String playerId, String playlist, Boolean isRanked) {
    return TEAM_SELECT_CLAUSE + FROM_CLAUSE + JOIN_GF_CLAUSE + JOIN_GA_CLAUSE +
        getWhereClause(playerId, playlist, isRanked) + ORDER_BY_CLAUSE + LIMIT_CLAUSE;
  }

}
