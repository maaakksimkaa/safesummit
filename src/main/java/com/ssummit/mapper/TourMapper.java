package com.ssummit.mapper;

import com.ssummit.dto.TourDto;
import com.ssummit.model.CheckpointMark;
import com.ssummit.model.GenericModel;
import com.ssummit.model.Tour;
import com.ssummit.repository.CheckpointMarkRepository;
import com.ssummit.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TourMapper extends GenericMapper<Tour, TourDto> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CheckpointMarkRepository checkpointMarkRepository;

    protected TourMapper(ModelMapper mapper, UserRepository userRepository, CheckpointMarkRepository checkpointMarkRepository) {
        super(mapper, Tour.class, TourDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.checkpointMarkRepository = checkpointMarkRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Tour.class, TourDto.class)
                .addMappings(m -> m.skip(TourDto::setParticipantsIds))
                .addMappings(m -> m.skip(TourDto::setCheckpointMarksIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(TourDto.class, Tour.class)
                .addMappings(m -> m.skip(Tour::setParticipants))
                .addMappings(m -> m.skip(Tour::setCheckpointMarks))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(TourDto source, Tour destination) {
        destination.setParticipants(userRepository.findAllByIdIn(source.getParticipantsIds()));
        destination.setCheckpointMarks(checkpointMarkRepository.findAllByIdIn(source.getCheckpointMarksIds()));
    }

    @Override
    void mapSpecificFields(Tour source, TourDto destination) {
        destination.setParticipantsIds(getParticipantsIds(source));
        destination.setCheckpointMarksIds(getCheckpointMarksIds(source));
    }

    private Set<Long> getParticipantsIds(Tour tour) {
        return Objects.isNull(tour) || Objects.isNull(tour.getId())
                ? null
                : tour.getParticipants().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }

    private Set<Long> getCheckpointMarksIds(Tour tour) {
        return Objects.isNull(tour) || Objects.isNull(tour.getId())
                ? null
                : tour.getCheckpointMarks().stream()
                .map(CheckpointMark::getId)
                .collect(Collectors.toSet());
    }
}
