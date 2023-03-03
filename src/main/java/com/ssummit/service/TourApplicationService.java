package com.ssummit.service;

import com.ssummit.dto.CreateTourApplicationDto;
import com.ssummit.model.TourApplication;
import com.ssummit.repository.TourApplicationRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
		return create(tourApplication);
	}

	@Override
	public TourApplication create(TourApplication tourApplication) {
		tourApplication.setCreatedBy("ADMIN");
		tourApplication.setUpdatedBy("ADMIN");
		return super.create(tourApplication);
	}

	@Override
	public TourApplication update(TourApplication tourApplication) {
		tourApplication.setUpdatedBy("ADMIN");
		return super.update(tourApplication);
	}

	@Override
	public void delete(Long id) {
		TourApplication tourApplication = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
		tourApplication.setUpdatedBy("ADMIN");
		super.update(tourApplication);
	}

}
