package com.ssummit.service;

import com.ssummit.dto.AddCheckpointMarkDto;
import com.ssummit.dto.AddRouteDto;
import com.ssummit.dto.AddTourDto;
import com.ssummit.model.*;
import com.ssummit.repository.RouteRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TourService extends GenericService<Tour> {
    private final TourRepository repository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final CheckpointMarkService checkpointMarkService;
    private final CheckpointService checkpointService;

    protected TourService(TourRepository repository, UserRepository userRepository, RouteRepository routeRepository, CheckpointMarkService checkpointMarkService, CheckpointService checkpointService) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.checkpointMarkService = checkpointMarkService;
        this.checkpointService = checkpointService;
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

    public Tour addCheckpointMark(AddCheckpointMarkDto addCheckpointMarkDto) {
        Tour tour = getOne(addCheckpointMarkDto.getTourId());
        Checkpoint checkpoint = checkpointService.getOne(addCheckpointMarkDto.getCheckpointId());
        CheckpointMark checkpointMark = new CheckpointMark();
        checkpointMark.setCheckpoint(checkpoint);
        checkpointMark.setScheduledMarkedTime(addCheckpointMarkDto.getScheduledMarkedTime());
        checkpointMarkService.create(checkpointMark);
        tour.getCheckpointMarks().add(checkpointMark);
        return update(tour);
    }

    public Set<String> getRouteCheckpoints(Long tourId) {
        return getOne(tourId).getRoute().getRouteCheckpoints().stream()
                .map(Checkpoint::getTitle).collect(Collectors.toSet());
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

    public Map<LocalDateTime, String> getScheduledTours() {
        List<Tour> tours = repository.findAll();
        Map<LocalDateTime, String> tourSchedule = new HashMap<>();
        for (Tour tour:
             tours) {
            tourSchedule.put(tour.getStartDate(), tour.getDescription());
        }
        return tourSchedule;
    }

}
