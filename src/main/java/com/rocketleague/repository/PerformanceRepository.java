package com.rocketleague.repository;

import com.rocketleague.entity.Performance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerformanceRepository extends CrudRepository<Performance, String> {

  List<Performance> findByPrimaryKeyIdPlayer(String idPlayer);
}
