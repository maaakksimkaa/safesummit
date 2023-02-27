package com.ssummit.rest.controller;

import com.ssummit.dto.UserDto;
import com.ssummit.mapper.UserMapper;
import com.ssummit.model.User;
import com.ssummit.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/user")
public class UserController extends GenericController<User, UserDto> {
	private final UserService service;
	private final UserMapper mapper;

	public UserController(UserService service, UserMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
