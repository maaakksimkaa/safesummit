package com.ssummit.service;

import com.ssummit.dto.AddTourDto;
import com.ssummit.model.Tour;
import com.ssummit.model.TourEquipment;
import com.ssummit.model.User;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class TourService extends GenericService<Tour> {
    private final TourRepository repository;
    private final UserRepository userRepository;

    protected TourService(TourRepository repository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public String getTourDescription(Long id) {
        return getOne(id).getDescription();
    }

    public TourEquipment getRequiredItems(Long id) {
        return getOne(id).getTourEquipment();
    }

    public Tour setPrimaryGuide(AddTourDto addTourDto) {
        User user = userRepository.findById(addTourDto.getUserId()).get();
        Tour tour = getOne(addTourDto.getTourId());
        tour.setPrimaryGuide(user);
        return update(tour);
    }


}
