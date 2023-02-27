package com.ssummit.rest.controller;

import com.ssummit.dto.CheckpointDto;
import com.ssummit.mapper.CheckpointMapper;
import com.ssummit.model.Checkpoint;
import com.ssummit.service.CheckpointService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/checkpoint")
public class CheckpointController extends GenericController<Checkpoint, CheckpointDto> {

	private final CheckpointService service;
	private final CheckpointMapper mapper;

	public CheckpointController(CheckpointService service, CheckpointMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
