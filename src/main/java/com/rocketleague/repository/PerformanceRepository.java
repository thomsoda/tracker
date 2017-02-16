package com.rocketleague.repository;

import com.rocketleague.entity.Performance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PerformanceRepository extends CrudRepository<Performance, String> {

  Set<Performance> findByPrimaryKeyIdPlayer(String idPlayer);
}
