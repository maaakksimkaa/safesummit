package com.ssummit.repository;

import com.ssummit.model.Tour;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TourRepository extends GenericRepository<Tour> {

    Set<Tour> findAllByIdIn(Set<Long> ids);
}
