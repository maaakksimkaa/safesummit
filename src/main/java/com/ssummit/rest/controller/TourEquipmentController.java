package com.ssummit.rest.controller;

import com.ssummit.dto.TourEquipmentDto;
import com.ssummit.mapper.TourEquipmentMapper;
import com.ssummit.model.TourEquipment;
import com.ssummit.service.TourEquipmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/tour-equipment")
public class TourEquipmentController extends GenericController<TourEquipment, TourEquipmentDto> {
	private final TourEquipmentService service;
	private final TourEquipmentMapper mapper;

	public TourEquipmentController(TourEquipmentService service, TourEquipmentMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
