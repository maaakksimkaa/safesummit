package com.ssummit.rest.controller;

import com.ssummit.dto.MessageDto;
import com.ssummit.mapper.MessageMapper;
import com.ssummit.model.Message;
import com.ssummit.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/message")
public class MessageController {
	private final MessageService service;
	private final MessageMapper mapper;

	public MessageController(MessageService service, MessageMapper mapper) {

		this.mapper = mapper;
		this.service = service;
	}

}
