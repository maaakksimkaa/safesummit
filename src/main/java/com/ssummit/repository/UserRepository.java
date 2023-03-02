package com.ssummit.repository;

import com.ssummit.model.Role;
import com.ssummit.model.User;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends GenericRepository<User> {

	Set<User> findAllByIdIn(Set<Long> ids);
	Set<User> findAllByRole(Role role);
	User findByEmail(String email);
	User findByChangePasswordToken(String token);
    User findUserByLogin(String login);
    Boolean existsByLogin(String login);
}
