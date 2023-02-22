package com.ssummit.rest.controller;

import com.ssummit.dto.TourDto;
import com.ssummit.dto.TourWithUsersDto;
import com.ssummit.mapper.TourMapper;
import com.ssummit.mapper.TourWithUsersMapper;
import com.ssummit.model.Tour;
import com.ssummit.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/tour")
public class TourController extends GenericController<Tour, TourDto> {
	private final TourService service;
	private final TourWithUsersMapper tourWithUsersMapper;

	public TourController(TourService service, TourMapper mapper, TourWithUsersMapper tourWithUsersMapper) {
		super(service, mapper);
		this.service = service;
		this.tourWithUsersMapper = tourWithUsersMapper;
	}

	@GetMapping("/scheduled_tours")
	public List<TourWithUsersDto> getScheduledTours() {
		return service.listAll().stream().map(tourWithUsersMapper::toDto).toList();
	}

	@GetMapping("/tour_description/{tourId}")
	public String getTourDescription(@PathVariable Long tourId) {
		return service.getTourDescription(tourId);
	}

}
