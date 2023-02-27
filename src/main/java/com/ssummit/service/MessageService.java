package com.ssummit.service;

import com.ssummit.model.Message;
import com.ssummit.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class MessageService {
	private final MessageRepository repository;

	protected MessageService(MessageRepository repository) {
		this.repository = repository;
	}

	public List<Message> listAll() {
		return repository.findAll();
	}

	public Message getOne(Long id) {
		return repository.findById(id).orElseThrow(() ->
				new NotFoundException("Row with such ID: " + id + " not found"));
	}

	public Message create(Message object) {
		return repository.save(object);
	}
}