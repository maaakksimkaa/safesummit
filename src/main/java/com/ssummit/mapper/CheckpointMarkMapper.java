package com.ssummit.mapper;

import com.ssummit.dto.CheckpointMarkDto;
import com.ssummit.model.CheckpointMark;
import com.ssummit.repository.CheckpointMarkRepository;
import com.ssummit.repository.CheckpointRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CheckpointMarkMapper extends GenericMapper<CheckpointMark, CheckpointMarkDto> {

    private final ModelMapper mapper;
    private final CheckpointMarkRepository repository;
    private final CheckpointRepository checkpointRepository;

    protected CheckpointMarkMapper(ModelMapper mapper, CheckpointMarkRepository repository, CheckpointRepository checkpointRepository) {
        super(mapper, CheckpointMark.class, CheckpointMarkDto.class);
        this.mapper = mapper;
        this.repository = repository;
        this.checkpointRepository = checkpointRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CheckpointMark.class, CheckpointMarkDto.class)
                .addMappings(m -> m.skip(CheckpointMarkDto::setCheckpointId)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(CheckpointMarkDto source, CheckpointMark destination) {
        destination.setCheckpoint(checkpointRepository.findById(source.getCheckpointId()).orElseThrow());
    }

    @Override
    void mapSpecificFields(CheckpointMark source, CheckpointMarkDto destination) {
        destination.setCheckpointId(source.getCheckpoint().getId());
    }
}
