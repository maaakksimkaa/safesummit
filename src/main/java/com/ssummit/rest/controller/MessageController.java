package com.ssummit.rest.controller;

import com.ssummit.dto.MessageDto;
import com.ssummit.mapper.MessageMapper;
import com.ssummit.model.Message;
import com.ssummit.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/message")
public class MessageController extends GenericController<Message, MessageDto> {
	private final MessageService service;
	private final MessageMapper mapper;

	public MessageController(MessageService service, MessageMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
