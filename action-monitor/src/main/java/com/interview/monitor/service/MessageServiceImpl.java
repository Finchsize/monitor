package com.interview.monitor.service;

import com.interview.monitor.model.dao.Message;
import com.interview.monitor.model.dao.User;
import com.interview.monitor.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message addMessage(String content, User user) {
        Message message = new Message();
        message.setContent(content);
        message.setUser(user);
        return messageRepository.save(message);
    }
}
