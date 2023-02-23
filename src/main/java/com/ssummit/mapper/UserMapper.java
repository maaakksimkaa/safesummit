package com.ssummit.mapper;

import com.ssummit.dto.UserDto;
import com.ssummit.model.User;
import com.ssummit.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends GenericMapper<User, UserDto> {

    private final ModelMapper mapper;

    private final UserRepository userRepository;

    public UserMapper(ModelMapper mapper, UserRepository userRepository) {
        super(mapper, User.class, UserDto.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

}
