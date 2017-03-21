package com.rocketleague.repository;

import com.rocketleague.entity.mapped.ArenaWinStats;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class GameRepositoryImpl implements GameRepositoryCustom {
  private static final String SELECT_CLAUSE = "select wl.id_player, g.arena, count(*) as played, " +
      "sum(case when wl.result = 'WIN' then 1 else 0 end) as wins, " +
      "sum(case when wl.result = 'LOSS' then 1 else 0 end) as losses, " +
      "sum(case when wl.result = 'WIN' then 1 else 0 end) / count(*) * 100 as win_percent ";
  private static final String FROM_CLAUSE = "from v_player_win_loss wl " +
      "join game g on g.id_game = wl.id_game ";
  private static final String WHERE_CLAUSE = "where wl.id_player = '{playerId}' and forfeit_ind = 0 ";
  private static final String ORDER_BY_CLAUSE = "order by sum(case when wl.result = 'WIN' then 1 else 0 end) / count(*) desc, g.arena ";
  private static final String GROUP_BY_CLAUSE = "group by wl.id_player, g.arena ";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<ArenaWinStats> findArenaWinPercents(String playerId, String playlist, Boolean isRanked) {
    Query query = entityManager.createNativeQuery(SELECT_CLAUSE + FROM_CLAUSE +
        getWhereClause(playerId, playlist, isRanked) + GROUP_BY_CLAUSE + ORDER_BY_CLAUSE, "ArenaWinStatsMapping");
    return query.getResultList();
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
}
