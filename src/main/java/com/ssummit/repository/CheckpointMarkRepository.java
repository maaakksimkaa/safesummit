package com.ssummit.repository;

import com.ssummit.model.CheckpointMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckpointMarkRepository extends JpaRepository<CheckpointMark, Long> {

    Set<CheckpointMark> findAllByIdIn(Set<Long> ids);
}
