package com.ssummit.service;

import com.ssummit.dto.MarkCheckpointDto;
import com.ssummit.model.CheckpointMark;
import com.ssummit.repository.CheckpointMarkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckpointMarkService {
	private final CheckpointMarkRepository repository;

	protected CheckpointMarkService(CheckpointMarkRepository repository) {
		this.repository = repository;
	}

	public List<CheckpointMark> getList() {
		return repository.findAll();
	}

	public CheckpointMark getOne(Long id) {
		return repository.findById(id).orElseThrow();
	}

	public CheckpointMark create(CheckpointMark object) {
		return repository.save(object);
	}

	public CheckpointMark update(CheckpointMark object) {
		return repository.save(object);
	}

	public CheckpointMark markCheckpoint(MarkCheckpointDto markCheckpointDto) {
		LocalDateTime actualMarkTime = markCheckpointDto.getActualMarkedTime();
		CheckpointMark checkpointMark = repository.findById(markCheckpointDto.getCheckpointMarkId()).get();
		checkpointMark.setActualMarkedTime(actualMarkTime);
		return update(checkpointMark);
	}
}
