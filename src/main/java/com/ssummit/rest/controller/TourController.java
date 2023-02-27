package com.ssummit.rest.controller;

import com.ssummit.dto.AddTourDto;
import com.ssummit.dto.TourDto;
import com.ssummit.mapper.TourMapper;
import com.ssummit.mapper.TourWithUsersMapper;
import com.ssummit.model.Tour;
import com.ssummit.model.TourEquipment;
import com.ssummit.service.TourService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tour")
@SecurityRequirement(name = "Bearer Authentication")
public class TourController extends GenericController<Tour, TourDto> {
	private final TourService service;
	private final TourWithUsersMapper tourWithUsersMapper;
	private final UserService userService;

	public TourController(TourService service, TourMapper mapper, TourWithUsersMapper tourWithUsersMapper, UserService userService) {
		super(service, mapper);
		this.service = service;
		this.tourWithUsersMapper = tourWithUsersMapper;
		this.userService = userService;
	}

	@Operation(description = "Просмотреть информацию о походе")
	@GetMapping("/tour_description/{tourId}")
	public String getTourDescription(@PathVariable Long tourId) {
		return service.getTourDescription(tourId);
	}

	@Operation(description = "Просмотреть список необходимого снаряжения")
	@GetMapping("/tour_equipment/{tourId}")
	public TourEquipment getTourEquipment(@PathVariable Long tourId) {
		return service.getRequiredItems(tourId);
	}

	@Operation(description = "Назначить первого гида на поход")
	@PostMapping("/set-primary-guide")
	public TourDto setPrimaryGuide(@RequestBody AddTourDto addTourDto) {
		return tourWithUsersMapper.toDto(service.setPrimaryGuide(addTourDto));
	}
}
