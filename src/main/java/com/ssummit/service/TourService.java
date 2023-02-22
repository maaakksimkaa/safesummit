package com.ssummit.service;

import com.ssummit.model.Tour;
import com.ssummit.repository.TourRepository;
import org.springframework.stereotype.Service;


@Service
public class TourService extends GenericService<Tour> {
    private final TourRepository repository;

    protected TourService(TourRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public String getTourDescription(Long id) {
        return repository.findById(id).get().getDescription();
    }

}
