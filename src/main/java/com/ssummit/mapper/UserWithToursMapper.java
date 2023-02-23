package com.ssummit.mapper;

import com.ssummit.dto.UserWithToursDto;
import com.ssummit.model.User;
import com.ssummit.repository.TourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
                .addMappings(m -> m.skip(UserWithToursDto::setToursIds)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserWithToursDto.class, User.class)
                .addMappings(m -> m.skip(User::setAssignedTours)).setPostConverter(toEntityConverter());
    }
}
