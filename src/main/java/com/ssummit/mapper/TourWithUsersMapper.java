package com.ssummit.mapper;

import com.ssummit.dto.TourWithUsersDto;
import com.ssummit.model.GenericModel;
import com.ssummit.model.Tour;
import com.ssummit.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TourWithUsersMapper extends GenericMapper<Tour, TourWithUsersDto> {

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    protected TourWithUsersMapper(ModelMapper mapper, UserRepository userRepository) {
        super(mapper, Tour.class, TourWithUsersDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Tour.class, TourWithUsersDto.class)
                .addMappings(m -> m.skip(TourWithUsersDto::setParticipantsIds)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TourWithUsersDto.class, Tour.class)
                .addMappings(m -> m.skip(Tour::setParticipants)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(TourWithUsersDto source, Tour destination) {
        destination.setParticipants(userRepository.findAllByIdIn(source.getParticipantsIds()));
    }

    @Override
    void mapSpecificFields(Tour source, TourWithUsersDto destination) {
        destination.setParticipantsIds(getIds(source));
    }

    private Set<Long> getIds(Tour tour) {
        return Objects.isNull(tour) || Objects.isNull(tour.getId())
                ? null
                : tour.getParticipants().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
