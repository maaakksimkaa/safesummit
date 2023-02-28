package com.ssummit.rest.controller;

import com.ssummit.dto.*;
import com.ssummit.mapper.TourGuidesAndParticipantsMapper;
import com.ssummit.mapper.TourMapper;
import com.ssummit.mapper.TourWithUsersMapper;
import com.ssummit.model.CheckpointMark;
import com.ssummit.model.Tour;
import com.ssummit.model.TourEquipment;
import com.ssummit.service.TourService;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/rest/tour")
public class TourController extends GenericController<Tour, TourDto> {
	private final TourService service;
	private final TourWithUsersMapper tourWithUsersMapper;
	private final UserService userService;
	private final TourMapper mapper;
	private final TourGuidesAndParticipantsMapper tourGuidesAndParticipantsMapper;

	public TourController(TourService service, TourMapper mapper, TourWithUsersMapper tourWithUsersMapper, UserService userService, TourGuidesAndParticipantsMapper tourGuidesAndParticipantsMapper) {
		super(service, mapper);
		this.service = service;
		this.tourWithUsersMapper = tourWithUsersMapper;
		this.userService = userService;
		this.mapper = mapper;
		this.tourGuidesAndParticipantsMapper = tourGuidesAndParticipantsMapper;
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

	@Operation(description = "Назначить помошника гида на поход")
	@PostMapping("/set-secondary-guide")
	public TourDto setSecondaryGuide(@RequestBody AddTourDto addTourDto) {
		return tourWithUsersMapper.toDto(service.setSecondaryGuide(addTourDto));
	}

	@Operation(description = "Прикрепить план похода(маршрут")
	@PostMapping("/set-route")
	public TourDto setRoute(@RequestBody AddRouteDto addRouteDto) {
		return mapper.toDto(service.setRoute(addRouteDto));
	}

	@Operation(description = "Добавить время прохождения контрольной точки")
	@PostMapping("/tour-set-checkpoint-scheduled-marked-time")
	public TourDto addCheckpointMark(@RequestBody AddCheckpointMarkDto addCheckpointMarkDto) {
		return mapper.toDto(service.addCheckpointMark(addCheckpointMarkDto));
	}

	@Operation(description = "Просмотреть список контрольных точек похода")
	@GetMapping("/tour_checkpoints_marks/{tourId}")
	public Set<CheckpointMark> getCheckpointsMarks(@PathVariable Long tourId) {
		return service.getCheckpointsMarks(tourId);
	}

	@Operation(description = "Просмотреть координаты последней пройденной контрольной точки")
	@GetMapping("/tour-last-checkpoint/{tourId}")
	public List<Double> getLastCheckpointCoordinates(@PathVariable Long tourId) {
		return service.getLastCheckpointCoordinates(tourId);
	}

	@Operation(description = "Просмотреть гидов и участников похода")
	@GetMapping("/tour-get-guides-and-participants/{tourId}")
	public TourGuidesAndParticipantsDto getGuidesAndParticipants(@PathVariable Long tourId) {
		return tourGuidesAndParticipantsMapper.toDto(service.getOne(tourId));
	}

	@Operation(description = "Просмотреть список контрольных точек на маршруте тура")
	@GetMapping("/tour-route-checkpoints/{tourId}")
	public Set<String> getRouteCheckpoints(@PathVariable Long tourId) {
		return service.getRouteCheckpoints(tourId);
	}

	@Operation(description = "Просмотреть расписание туров")
	@GetMapping("/tour-get-scheduled-tours")
	public Map<Date, String> getScheduledTours() {
		return service.getScheduledTours();
	}
}
