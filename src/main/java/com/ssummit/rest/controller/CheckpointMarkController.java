package com.ssummit.rest.controller;

import com.ssummit.mapper.CheckpointMarkMapper;
import com.ssummit.service.CheckpointMarkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/checkpoit-mark")
public class CheckpointMarkController {

	private final CheckpointMarkService service;
	private final CheckpointMarkMapper mapper;

	public CheckpointMarkController(CheckpointMarkService service, CheckpointMarkMapper mapper) {
		this.mapper = mapper;
		this.service = service;
	}

}
