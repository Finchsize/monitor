package com.interview.monitor.repository;

import com.interview.monitor.model.dao.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
