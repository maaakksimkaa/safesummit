package com.ssummit.service;

import com.ssummit.model.Checkpoint;
import com.ssummit.repository.CheckpointRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class CheckpointService extends GenericService<Checkpoint> {
	private final CheckpointRepository repository;

	protected CheckpointService(CheckpointRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Checkpoint create(Checkpoint checkpoint) {
		checkpoint.setCreatedBy("ADMIN");
		checkpoint.setUpdatedBy("ADMIN");
		return super.create(checkpoint);
	}

	@Override
	public Checkpoint update(Checkpoint checkpoint) {
		checkpoint.setUpdatedBy("ADMIN");
		return super.update(checkpoint);
	}

	@Override
	public void delete(Long id) {
		Checkpoint checkpoint = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
		checkpoint.setUpdatedBy("ADMIN");
		super.update(checkpoint);
	}
}
