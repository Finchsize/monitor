package com.interview.monitor.service;

import com.interview.monitor.model.dao.Message;
import com.interview.monitor.model.dao.User;

public interface MessageService {
    Message addMessage(String content, User user);
}
