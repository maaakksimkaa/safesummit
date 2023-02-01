package com.ssummit.mapper;

import com.ssummit.dto.TourDto;
import com.ssummit.model.Tour;
import com.ssummit.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends GenericMapper<Tour, TourDto> {

    private final ModelMapper mapper;

    private final TourRepository tourRepository;

    protected TourMapper(ModelMapper mapper, TourRepository tourRepository) {
        super(mapper, Tour.class, TourDto.class);
        this.mapper = mapper;
        this.tourRepository = tourRepository;
    }
}
