package com.ssummit.rest.controller;

import com.ssummit.dto.TourDto;
import com.ssummit.mapper.TourMapper;
import com.ssummit.model.Tour;
import com.ssummit.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/tour")
public class TourController extends GenericController<Tour, TourDto> {
	private final TourService service;
	private final TourMapper mapper;

	public TourController(TourService service, TourMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
