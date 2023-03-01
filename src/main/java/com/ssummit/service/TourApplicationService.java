package com.ssummit.service;

import com.ssummit.dto.CreateTourApplicationDto;
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

	public TourApplication create(CreateTourApplicationDto createTourApplicationDto) {
		TourApplication tourApplication = new TourApplication();
		tourApplication.setTitle(createTourApplicationDto.getTitle());
		tourApplication.setDesctiption(createTourApplicationDto.getDescription());
		tourApplication.setOutcomingPostNumber(createTourApplicationDto.getOutcomingPostNumber());
		tourApplication.setApplicationDate(createTourApplicationDto.getApplicationDate());
		tourApplication.setIncomingPostNumber(createTourApplicationDto.getIncomingPostNumber());
		tourApplication.setApplicationRegistrationDate(createTourApplicationDto.getApplicationRegistrationDate());
		return super.create(tourApplication);
	}

}
