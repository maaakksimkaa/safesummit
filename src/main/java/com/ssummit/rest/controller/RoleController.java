package com.ssummit.rest.controller;

import com.ssummit.model.Role;
import com.ssummit.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/role")
public class RoleController {

	private final RoleService service;

	public RoleController(RoleService service) {
		this.service = service;
	}

	@GetMapping("/list")
	public List<Role> list() {
		return service.getList();
	}

}
