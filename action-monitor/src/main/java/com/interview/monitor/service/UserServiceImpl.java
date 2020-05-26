package com.interview.monitor.service;

import com.interview.monitor.model.dao.User;
import com.interview.monitor.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUser(String name) {
        User user = userRepository.findByName(name);
        if(user == null) {
            throw new NoSuchElementException("User with name: " + name + " does not exist.");
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
