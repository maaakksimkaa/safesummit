package com.ssummit.service;

import com.ssummit.model.Checkpoint;
import com.ssummit.repository.CheckpointRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckpointService extends GenericService<Checkpoint> {
	private final CheckpointRepository repository;

	protected CheckpointService(CheckpointRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
