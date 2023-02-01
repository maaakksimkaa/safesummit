package com.ssummit.mapper;

import com.ssummit.dto.TourEquipmentDto;
import com.ssummit.model.TourEquipment;
import com.ssummit.repository.TourEquipmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TourEquipmentMapper extends GenericMapper<TourEquipment, TourEquipmentDto> {

    private final ModelMapper mapper;

    private final TourEquipmentRepository tourEquipmentRepository;

    public TourEquipmentMapper(ModelMapper mapper, TourEquipmentRepository tourEquipmentRepository) {
        super(mapper, TourEquipment.class, TourEquipmentDto.class);
        this.mapper = mapper;
        this.tourEquipmentRepository = tourEquipmentRepository;
    }
}
