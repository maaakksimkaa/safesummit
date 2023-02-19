package com.ssummit.service;

import com.ssummit.model.Route;
import com.ssummit.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService extends GenericService<Route>{
	private final RouteRepository repository;
	protected RouteService (RouteRepository repository) {
		super(repository);
		this.repository=repository;
	}
}
