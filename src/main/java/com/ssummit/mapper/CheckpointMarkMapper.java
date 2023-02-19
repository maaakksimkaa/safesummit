package com.ssummit.mapper;

import com.ssummit.dto.CheckpointMarkDto;
import com.ssummit.model.CheckpointMark;
import com.ssummit.repository.CheckpointMarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CheckpointMarkMapper {

    private final ModelMapper mapper;

    private final CheckpointMarkRepository repository;

    protected CheckpointMarkMapper(ModelMapper mapper, CheckpointMarkRepository repository) {

        this.mapper = mapper;
        this.repository = repository;
    }


}
