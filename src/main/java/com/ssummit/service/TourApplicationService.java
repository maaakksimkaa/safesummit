package com.ssummit.service;

import com.ssummit.model.TourApplication;
import com.ssummit.repository.TourApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class TourApplicationService extends GenericService<TourApplication> {
	private final TourApplicationRepository repository;
	protected TourApplicationService (TourApplicationRepository repository){
		super(repository);
		this.repository=repository;
	}
}
