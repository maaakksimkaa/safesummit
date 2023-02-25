package com.ssummit.service;

import com.ssummit.dto.AddRouteDto;
import com.ssummit.dto.AddTourDto;
import com.ssummit.model.*;
import com.ssummit.repository.RouteRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Service
public class TourService extends GenericService<Tour> {
    private final TourRepository repository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    protected TourService(TourRepository repository, UserRepository userRepository, RouteRepository routeRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
    }

    public String getTourDescription(Long id) {
        return getOne(id).getDescription();
    }

    public TourEquipment getRequiredItems(Long tourId) {
        return getOne(tourId).getTourEquipment();
    }

    public Tour setPrimaryGuide(AddTourDto addTourDto) {
        User user = userRepository.findById(addTourDto.getUserId()).get();
        Tour tour = getOne(addTourDto.getTourId());
        tour.setPrimaryGuide(user);
        return update(tour);
    }

    public Tour setSecondaryGuide(AddTourDto addTourDto) {
        User user = userRepository.findById(addTourDto.getUserId()).get();
        Tour tour = getOne(addTourDto.getTourId());
        tour.setSecondaryGuide(user);
        return update(tour);
    }

    public Tour setRoute(AddRouteDto addRouteDto) {
        Tour tour = getOne(addRouteDto.getTourId());
        Route route = routeRepository.findById(addRouteDto.getRouteId()).get();
        tour.setRoute(route);
        return update(tour);
    }

    public Set<CheckpointMark> getCheckpointsMarks(Long tourId) {
        return getOne(tourId).getCheckpointMarks();
    }

    public List<Double> getLastCheckpointCoordinates(Long tourId) {
        Set<CheckpointMark> checkpointMarks = getOne(tourId).getCheckpointMarks();
        CheckpointMark lastCheckpointMark = checkpointMarks.stream()
                .max(Comparator.comparing(CheckpointMark::getActualMarkedTime))
                .get();
        Checkpoint lastCheckpoint = lastCheckpointMark.getCheckpoint();
        List<Double> lastCheckpointCoordinates = new ArrayList<>();
        lastCheckpointCoordinates.add(lastCheckpoint.getLatitude());
        lastCheckpointCoordinates.add(lastCheckpoint.getLongitude());
        return lastCheckpointCoordinates;
    }

}
