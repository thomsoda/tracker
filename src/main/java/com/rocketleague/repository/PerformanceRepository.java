package com.rocketleague.repository;

import com.rocketleague.entity.Performance;
import com.rocketleague.refdata.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerformanceRepository extends CrudRepository<Performance, String> {

  List<Performance> findByPrimaryKeyIdPlayer(String idPlayer);

  List<Performance> findByPrimaryKeyGameIdGameAndTeamOrderByScoreDesc(int idGame, Team team);
}
