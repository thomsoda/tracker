package com.rocketleague.repository;

import com.rocketleague.entity.Performance;
import org.springframework.data.repository.CrudRepository;

public interface FormRepository extends CrudRepository<Performance, String>, FormRepositoryCustom {
}
