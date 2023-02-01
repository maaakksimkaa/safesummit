package com.ssummit.repository;

import com.ssummit.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends GenericRepository<Message> {
}
