package com.interview.monitor.service;

import com.interview.monitor.model.dao.User;

public interface UserService {
    User findUser(String name);
    User addUser(User user);
}
