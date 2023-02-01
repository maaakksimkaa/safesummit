package com.ssummit.rest.controller;

import com.ssummit.dto.CheckpointMarkDto;
import com.ssummit.mapper.CheckpointMarkMapper;
import com.ssummit.model.CheckpointMark;
import com.ssummit.service.CheckpointMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/rest/checkpoit-mark")
public class CheckpointMarkController {

	private final CheckpointMarkService service;
	private final CheckpointMarkMapper mapper;

	public CheckpointMarkController(CheckpointMarkService service, CheckpointMarkMapper mapper) {
		this.mapper = mapper;
		this.service = service;
	}

}
