package com.ssummit.service;

import com.ssummit.dto.AddCheckpointToRouteDto;
import com.ssummit.model.Checkpoint;
import com.ssummit.model.Route;
import com.ssummit.repository.CheckpointRepository;
import com.ssummit.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends GenericService<Route>{
	private final RouteRepository repository;

	private final CheckpointRepository checkpointRepository;
	protected RouteService (RouteRepository repository, CheckpointRepository checkpointRepository) {
		super(repository);
		this.repository=repository;
		this.checkpointRepository = checkpointRepository;
	}

	public Route addCheckpoint(AddCheckpointToRouteDto addCheckpointToRouteDto) {
		Route route = getOne(addCheckpointToRouteDto.getRouteId());
		Checkpoint checkpoint = checkpointRepository.findById(addCheckpointToRouteDto.getCheckpointId()).get();
		route.getRouteCheckpoints().add(checkpoint);
		return update(route);
	}
}
