package com.ssummit.service;

import com.ssummit.dto.LoginDTO;
import com.ssummit.model.User;
import com.ssummit.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	protected UserService (UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		super(userRepository);
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public boolean checkPassword(LoginDTO loginDTO) {
		System.out.println(loginDTO);
		return bCryptPasswordEncoder.matches(loginDTO.getPassword(),
				getByUserName(loginDTO.getLogin()).getPassword());
	}

	public User getByUserName(final String name) {
		return userRepository.findUserByLogin(name);
	}

}