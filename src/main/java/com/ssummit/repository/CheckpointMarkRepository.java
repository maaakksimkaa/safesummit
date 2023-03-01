package com.ssummit.repository;

import com.ssummit.model.CheckpointMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface CheckpointMarkRepository extends JpaRepository<CheckpointMark, Long> {
	CheckpointMark getFirstByActualMarkedTimeNullAndActualMarkedTimeBeforeOrderByScheduledMarkedTimeDesc(LocalDateTime actualMarkedTime);
	CheckpointMark getFirstByActualMarkedTimeNotNullOrderByActualMarkedTimeDesc();
	CheckpointMark getFirstByScheduledMarkedTimeAndActualMarkedTimeNullOrderByScheduledMarkedTimeAsc(LocalDateTime scheduledMarkedTime);
	CheckpointMark findFirstByScheduledMarkedTimeAndActualMarkedTimeAfterOrderByActualMarkedTimeAsc(@Nullable LocalDateTime scheduledMarkedTime, LocalDateTime actualMarkedTime);

    Set<CheckpointMark> findAllByIdIn(Set<Long> ids);
}
