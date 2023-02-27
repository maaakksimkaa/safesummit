package com.ssummit.rest.controller;

import com.ssummit.dto.TourApplicationDto;
import com.ssummit.mapper.TourApplicationMapper;
import com.ssummit.model.TourApplication;
import com.ssummit.service.TourApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/tour-application")
public class TourApplicationController extends GenericController<TourApplication, TourApplicationDto> {
	private final TourApplicationService service;
	private final TourApplicationMapper mapper;

	public TourApplicationController(TourApplicationService service, TourApplicationMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
