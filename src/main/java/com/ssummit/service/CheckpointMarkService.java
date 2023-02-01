package com.ssummit.service;

import com.ssummit.model.CheckpointMark;
import com.ssummit.repository.CheckpointMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckpointMarkService {
	private final CheckpointMarkRepository repository;

	protected CheckpointMarkService(CheckpointMarkRepository repository) {
		this.repository = repository;
	}
}
