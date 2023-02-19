package com.ssummit.mapper;

import com.ssummit.dto.TourApplicationDto;
import com.ssummit.model.TourApplication;
import com.ssummit.repository.TourApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TourApplicationMapper extends GenericMapper<TourApplication, TourApplicationDto> {

    private final ModelMapper mapper;

    private final TourApplicationRepository tourApplicationRepository;

    protected TourApplicationMapper(ModelMapper mapper, TourApplicationRepository tourApplicationRepository) {
        super(mapper, TourApplication.class, TourApplicationDto.class);
        this.mapper = mapper;
        this.tourApplicationRepository = tourApplicationRepository;
    }
}