package com.ssummit.mapper;

import com.ssummit.dto.UserWithToursDto;
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
public class UserWithToursMapper extends GenericMapper<User, UserWithToursDto> {

    private final ModelMapper mapper;
    private final TourRepository tourRepository;


    protected UserWithToursMapper(ModelMapper mapper, TourRepository tourRepository) {
        super(mapper, User.class, UserWithToursDto.class);
        this.mapper = mapper;
        this.tourRepository = tourRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserWithToursDto.class)
                .addMappings(m -> m.skip(UserWithToursDto::setAssignedToursIds)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserWithToursDto.class, User.class)
                .addMappings(m -> m.skip(User::setAssignedTours)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(UserWithToursDto source, User destination) {
        destination.setAssignedTours(tourRepository.findAllByIdIn(source.getAssignedToursIds()));
    }

    @Override
    void mapSpecificFields(User source, UserWithToursDto destination) {
        destination.setAssignedToursIds(getIds(source));
    }

    private Set<Long> getIds(User user) {
        return Objects.isNull(user) || Objects.isNull(user.getId())
                ? null
                : user.getAssignedTours().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
