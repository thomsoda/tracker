package com.rocketleague.repository;

import com.rocketleague.entity.TrackedPlayer;
import org.springframework.data.repository.CrudRepository;

public interface TrackedPlayerRepository extends CrudRepository<TrackedPlayer, String> {

  Iterable<TrackedPlayer> findAllByOrderByIdPlayer();

}
