package com.rocketleague.repository;

import com.rocketleague.entity.Performance;
import com.rocketleague.entity.mapped.PlayerContribution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContributionRepository extends CrudRepository<Performance, String>, ContributionRepositoryCustom {

}
