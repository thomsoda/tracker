package com.rocketleague.repository;

import com.rocketleague.entity.TrackedPlayer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TrackedPlayerRepository extends CrudRepository<TrackedPlayer, String>, QueryDslPredicateExecutor, TrackedPlayerRepositoryCustom {

  Iterable<TrackedPlayer> findAllByOrderByIdPlayer();

}
