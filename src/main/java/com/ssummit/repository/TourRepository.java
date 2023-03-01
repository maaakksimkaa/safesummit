package com.ssummit.repository;

import com.ssummit.model.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TourRepository extends GenericRepository<Tour> {
	Tour getFirstByCheckpointMarks_ActualMarkedTimeNullAndCheckpointMarks_ScheduledMarkedTimeBeforeOrderByCheckpointMarks_ScheduledMarkedTimeAsc(LocalDateTime scheduledMarkedTime);
	List<Tour> findByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

	Set<Tour> findAllByIdIn(Set<Long> ids);
}
