package com.ssummit.rest.controller;

import com.ssummit.dto.AddTourDto;
import com.ssummit.dto.UserDto;
import com.ssummit.dto.UserWithToursDto;
import com.ssummit.mapper.UserMapper;
import com.ssummit.mapper.UserWithToursMapper;
import com.ssummit.model.User;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/rest/user")
public class UserController extends GenericController<User, UserDto> {
	private final UserService service;
	private final UserWithToursMapper userWithToursMapper;

	public UserController(UserService service, UserMapper mapper, UserWithToursMapper userWithToursMapper) {
		super(service, mapper);
		this.service = service;
		this.userWithToursMapper = userWithToursMapper;
	}

	@Operation(description = "Зарегистрироваться на тур")
	@PostMapping("/tour-register")
	public UserDto addTour(@RequestBody AddTourDto addTourDto) {
		return userWithToursMapper.toDto(service.addTour(addTourDto));
	}

	@Operation(description = "Отменить участие в туре")
	@PostMapping("/tour-revoke")
	public UserDto revokeTour(@RequestBody AddTourDto addTourDto) {
		return userWithToursMapper.toDto(service.revokeTour(addTourDto));
	}

	@Operation(description = "Просмотреть список туров, в которых участвует пользователь")
	@GetMapping("/scheduled_tours")
	public List<UserWithToursDto> getScheduledTours() {
		return service.listAll().stream().map(userWithToursMapper::toDto).toList();
	}

	@Operation(description = "Просмотреть список всех гидов")
	@GetMapping("/list-all-guides")
	public List<UserWithToursDto> getAllGuides() {
		return service.getAllGuides().stream().map(userWithToursMapper::toDto).toList();
	}

	@Operation(description = "Просмотреть список всех участников")
	@GetMapping("/list-all-participants")
	public List<UserWithToursDto> getAllParticipants() {
		return service.getAllParticipants().stream().map(userWithToursMapper::toDto).toList();
	}


}
