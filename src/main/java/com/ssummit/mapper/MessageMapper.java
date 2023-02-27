package com.ssummit.mapper;

import com.ssummit.dto.MessageDto;
import com.ssummit.model.Message;
import com.ssummit.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    private final ModelMapper mapper;

    private final MessageRepository messageRepository;

    protected MessageMapper(ModelMapper mapper, MessageRepository messageRepository) {

        this.mapper = mapper;
        this.messageRepository = messageRepository;
    }
}
