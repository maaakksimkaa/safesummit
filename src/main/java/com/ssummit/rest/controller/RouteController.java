package com.ssummit.rest.controller;

import com.ssummit.dto.RouteDto;
import com.ssummit.mapper.RouteMapper;
import com.ssummit.model.Route;
import com.ssummit.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/route")
public class RouteController extends GenericController<Route, RouteDto> {
	private final RouteService service;
	private final RouteMapper mapper;

	public RouteController(RouteService service, RouteMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}