package com.rocketleague.repository;

import com.rocketleague.entity.TrackedPlayer;
import org.springframework.data.repository.CrudRepository;

public interface TrackedPlayerRepository extends CrudRepository<TrackedPlayer, String>, TrackedPlayerRepositoryCustom {

  Iterable<TrackedPlayer> findAllByOrderByIdPlayer();
  Iterable<TrackedPlayer> findAll();

}
