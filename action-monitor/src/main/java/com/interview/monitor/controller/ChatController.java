package com.interview.monitor.controller;

import com.interview.monitor.model.dao.User;
import com.interview.monitor.model.dto.ChatMessage;
import com.interview.monitor.service.MessageService;
import com.interview.monitor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.NoSuchElementException;

@Controller
@AllArgsConstructor
public class ChatController {

    private final UserService userService;
    private final MessageService messageService;

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws NoSuchElementException {
        User user = userService.findUser(chatMessage.getSender());
        messageService.addMessage(chatMessage.getContent(), user);
        return chatMessage;
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        try {
            userService.findUser(chatMessage.getSender());
        } catch (NoSuchElementException e) {
            User user = new User();
            user.setName(chatMessage.getSender());
            userService.addUser(user);
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
