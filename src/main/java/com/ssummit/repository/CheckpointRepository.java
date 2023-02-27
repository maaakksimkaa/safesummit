package com.ssummit.repository;

import com.ssummit.model.Checkpoint;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckpointRepository extends GenericRepository<Checkpoint> {

    Set<Checkpoint> findAllByIdIn(Set<Long> ids);
}
