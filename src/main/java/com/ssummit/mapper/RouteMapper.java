package com.ssummit.mapper;

import com.ssummit.dto.RouteDto;
import com.ssummit.model.Route;
import com.ssummit.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper extends GenericMapper<Route, RouteDto> {

    private final ModelMapper mapper;

    private final RouteRepository routeRepository;

    protected RouteMapper(ModelMapper mapper, RouteRepository routeRepository) {
        super(mapper, Route.class, RouteDto.class);
        this.mapper = mapper;
        this.routeRepository = routeRepository;
    }
}
