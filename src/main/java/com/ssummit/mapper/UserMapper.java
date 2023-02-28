package com.ssummit.mapper;

import com.ssummit.dto.UserDto;
import com.ssummit.model.GenericModel;
import com.ssummit.model.User;
import com.ssummit.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDto> {

    private final ModelMapper mapper;

    private final TourRepository tourRepository;

    public UserMapper(ModelMapper mapper, TourRepository tourRepository) {
        super(mapper, User.class, UserDto.class);
        this.mapper = mapper;
        this.tourRepository = tourRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setAssignedToursIds)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDto.class, User.class)
                .addMappings(m -> m.skip(User::setAssignedTours)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(UserDto source, User destination) {
        destination.setAssignedTours(tourRepository.findAllByIdIn(source.getAssignedToursIds()));
    }

    @Override
    void mapSpecificFields(User source, UserDto destination) {
        destination.setAssignedToursIds(getAssignedToursIds(source));
    }

    private Set<Long> getAssignedToursIds(User user) {
        return Objects.isNull(user) || Objects.isNull(user.getId())
                ? null
                : user.getAssignedTours().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }

}
