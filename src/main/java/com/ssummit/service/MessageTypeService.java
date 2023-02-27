package com.ssummit.service;

import com.ssummit.model.MessageType;
import com.ssummit.repository.MessageTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageTypeService {
	private final MessageTypeRepository repository;

	public MessageTypeService(MessageTypeRepository repository) {
		this.repository = repository;
	}

	public List<MessageType> getList() {
		return repository.findAll();
	}

	public MessageType getOne(Long id) {
		return repository.findById(id).orElseThrow();
	}
	public String getMessageTypeDescription(Long id) {
		return getOne(id).getDescription();
	}

}

