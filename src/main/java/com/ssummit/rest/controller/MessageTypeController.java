package com.ssummit.rest.controller;

import com.ssummit.model.MessageType;
import com.ssummit.service.MessageTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message-type")
public class MessageTypeController {
	private final MessageTypeService service;

	public MessageTypeController(MessageTypeService service) {
		this.service = service;
	}
	@GetMapping("/list")
	public List<MessageType> list() {
		return service.getList();
	}
}
