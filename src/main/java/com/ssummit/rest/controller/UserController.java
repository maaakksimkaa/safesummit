package com.ssummit.rest.controller;

import com.ssummit.dto.AddTourDto;
import com.ssummit.dto.RestorePasswordWithEmailDTO;
import com.ssummit.dto.UserDto;
import com.ssummit.dto.UserWithToursDto;
import com.ssummit.mapper.UserMapper;
import com.ssummit.mapper.UserWithToursMapper;
import com.ssummit.model.User;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/user")
public class UserController extends GenericController<User, UserDto> {
	private final UserService service;
	private final UserWithToursMapper userWithToursMapper;
	private final UserMapper userMapper;

	public UserController(UserService service, UserMapper mapper, UserWithToursMapper userWithToursMapper, UserMapper userMapper) {
		super(service, mapper);
		this.service = service;
		this.userWithToursMapper = userWithToursMapper;
		this.userMapper = userMapper;
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
	@GetMapping("/restore-password")
	public String rememberPassword() {
		return "rememberPassword";
	}

	@PostMapping("/restore-password")
	public String rememberPassword(@ModelAttribute("email") RestorePasswordWithEmailDTO rememberPasswordDto) {
		UserDto userDto = userMapper.toDto(service.getUserByEmail(rememberPasswordDto.getEmail()));
		if(Objects.isNull(userDto)) {
			return "redirect:/error-with-message?message=User not found";
		} else {
			service.sendChangePasswordEmail(userDto.getEmail());
			return "redirect:/login";
		}
	}

	@GetMapping("/change-password")
	public String changePassword(@PathParam(value = "uuid") String uuid, Model model) {
		model.addAttribute("uuid", uuid);
		return "changePassword";
	}


	@PostMapping("/change-password")
	public String changePassword(@PathParam(value = "uuid") String uuid, @ModelAttribute("changePasswordForm") UserDto userDto) {
		service.changePassword(uuid, userDto.getPassword());
		return "redirect:/login";
	}

}
