package com.ssummit.service;

import com.ssummit.model.User;
import com.ssummit.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService extends GenericService<User> {
	private final UserRepository repository;
	protected UserService (UserRepository repository){
		super(repository);
		this.repository=repository;
	}
}