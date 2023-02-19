package com.ssummit.service;

import com.ssummit.model.Message;
import com.ssummit.repository.MessageRepository;
import org.springframework.stereotype.Service;
@Service
public class MessageService extends GenericService<Message> {
	private final MessageRepository repository;
	protected MessageService (MessageRepository repository){
		super(repository);
		this.repository=repository;
	}
}
