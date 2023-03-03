package com.ssummit.service;

import com.ssummit.dto.AddCheckpointToRouteDto;
import com.ssummit.model.Checkpoint;
import com.ssummit.model.Route;
import com.ssummit.repository.CheckpointRepository;
import com.ssummit.repository.RouteRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
		Checkpoint checkpoint = checkpointRepository.findById(addCheckpointToRouteDto.getCheckpointId()).orElseThrow();
		route.getRouteCheckpoints().add(checkpoint);
		return update(route);
	}

	@Override
	public Route create(Route route) {
		route.setCreatedBy("ADMIN");
		route.setUpdatedBy("ADMIN");
		return super.create(route);
	}

	@Override
	public Route update(Route route) {
		route.setUpdatedBy("ADMIN");
		return super.update(route);
	}

	@Override
	public void delete(Long id) {
		Route route = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
		route.setUpdatedBy("ADMIN");
		super.update(route);
	}
}
