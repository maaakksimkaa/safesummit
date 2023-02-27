package com.ssummit.repository;

import com.ssummit.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends GenericRepository<User> {
    User findUserByLogin(String login);
    Boolean existsByLogin(String login);
}
