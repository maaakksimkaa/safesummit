package com.ssummit.rest.controller;

import com.ssummit.dto.*;
import com.ssummit.mapper.TourGuidesAndParticipantsMapper;
import com.ssummit.mapper.TourMapper;
import com.ssummit.mapper.TourWithUsersMapper;
import com.ssummit.model.Tour;
import com.ssummit.model.TourApplication;
import com.ssummit.service.TourApplicationService;
import com.ssummit.service.TourService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/tour")
@SecurityRequirement(name = "Bearer Authentication")
public class TourController extends GenericController<Tour, TourDto> {
	private final TourService service;
	private final TourWithUsersMapper tourWithUsersMapper;
	private final UserService userService;
	private final TourMapper mapper;
	private final TourGuidesAndParticipantsMapper tourGuidesAndParticipantsMapper;
	private final TourApplicationService tourApplicationService;

	public TourController(TourService service, TourMapper mapper, TourWithUsersMapper tourWithUsersMapper, UserService userService, TourGuidesAndParticipantsMapper tourGuidesAndParticipantsMapper, TourApplicationService tourApplicationService) {
		super(service, mapper);
		this.service = service;
		this.tourWithUsersMapper = tourWithUsersMapper;
		this.userService = userService;
		this.mapper = mapper;
		this.tourGuidesAndParticipantsMapper = tourGuidesAndParticipantsMapper;
		this.tourApplicationService = tourApplicationService;
	}

	@Operation(description = "Добавить поход в расписание")
	@PostMapping("/schedule-a-tour")
	public Tour scheduleTour(@RequestBody ScheduleTourDto scheduleTourDto) {
		return service.scheduleTour(scheduleTourDto);
	}

	@Operation(description = "Просмотреть информацию о походе")
	@GetMapping("/tour-description/{tourId}")
	public String getTourDescription(@PathVariable Long tourId) {
		return service.getTourDescription(tourId);
	}

//	@Operation(description = "Просмотреть список необходимого снаряжения")
//	@GetMapping("/tour_equipment/{tourId}")
//	public TourEquipment getTourEquipment(@PathVariable Long tourId) {
//		return service.getRequiredItems(tourId);
//	}

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
	@GetMapping("/tour-checkpoints-marks/{tourId}")
	public Map<String, LocalDateTime> getCheckpointsMarks(@PathVariable Long tourId) {
		return service.getScheduledCheckpointMarks(tourId);
	}

	@Operation(description = "Просмотреть координаты последней пройденной контрольной точки")
	@GetMapping("/tour-last-checkpoint/{tourId}")
	public List<Double> getLastMarkedCheckpointCoordinates(@PathVariable Long tourId) {
		return service.getLastCheckpointCoordinates(tourId);
	}

	@Operation(description = "Просмотреть гидов и участников похода")
	@GetMapping("/tour-get-guides-and-participants/{tourId}")
	public TourGuidesAndParticipantsDto getGuidesAndParticipants(@PathVariable Long tourId) {
		return service.getTourGuidesAndParticipants(tourId);
	}

	@Operation(description = "Просмотреть список контрольных точек на маршруте тура")
	@GetMapping("/tour-route-checkpoints/{tourId}")
	public Set<String> getRouteCheckpoints(@PathVariable Long tourId) {
		return service.getRouteCheckpoints(tourId);
	}


	@Operation(description = "Просмотреть расписание туров")
	@GetMapping("/tour-get-scheduled-tours")
	public Map<LocalDateTime, String> getScheduledTours() {
		return service.getScheduledTours();
	}

	@Operation(description = "Просмотреть список необходимого снаряжения")
	@GetMapping("/tour-get-required-equipment/{tourId}")
	public Set<String> getRequiredEquipment(@PathVariable Long tourId) {
		return service.getRequiredItemTypes(tourId);
	}

	@Operation(description = "Заполнить заявку на регистрацию в МЧС")
	@PostMapping("/tour-fill-tour-application{tourId}")
	public String addTourApplication(@PathVariable Long tourId) {
		return tourApplicationService.sendTourApplication(tourId);
	}

	@Operation(description = "Финальная проверка перед выходом")
	@GetMapping("/tour-final-check/{tourId}")
	public String checkTourBeforeDeparture(@PathVariable Long tourId) {
		return service.checkTourBeforeDeparture(tourId);
	}
}
