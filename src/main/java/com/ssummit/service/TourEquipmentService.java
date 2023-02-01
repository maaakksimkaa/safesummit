package com.ssummit.service;

import com.ssummit.model.TourEquipment;
import com.ssummit.repository.TourEquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TourEquipmentService extends GenericService<TourEquipment> {
	private final TourEquipmentRepository repository;
	protected TourEquipmentService (TourEquipmentRepository repository){
		super(repository);
		this.repository=repository;
	}
}