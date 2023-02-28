package com.ssummit.mapper;

import com.ssummit.dto.CheckpointDto;
import com.ssummit.model.Checkpoint;
import com.ssummit.repository.CheckpointRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CheckpointMapper extends GenericMapper<Checkpoint, CheckpointDto> {

    private final ModelMapper mapper;

    private final CheckpointRepository checkpointRepository;

    protected CheckpointMapper(ModelMapper mapper, CheckpointRepository checkpointRepository) {
        super(mapper, Checkpoint.class, CheckpointDto.class);
        this.mapper = mapper;
        this.checkpointRepository = checkpointRepository;
    }
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Checkpoint.class, CheckpointDto.class)
//              .addMappings(m -> m.skip(CheckpointDto::setId)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(CheckpointDto.class, Checkpoint.class)
//              .setPostConverter(toEntityConverter());
//    }

}
