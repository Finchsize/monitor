package com.interview.monitor.service;

import com.interview.monitor.model.dao.Message;
import com.interview.monitor.model.dao.User;
import com.interview.monitor.repository.MessageRepository;
import com.interview.monitor.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class MessageServiceTests {

    private final String CONTENT = "content";
    private final User USER = new User();
    @Mock
    private MessageRepository messageRepository;
    private MessageService messageService;
    private Message message;

    @Before
    public void setUp() {
        message = new Message();
        message.setContent(CONTENT);
        message.setUser(USER);
        Mockito.when(messageRepository.save(message))
                .thenReturn(message);

        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    public void whenValidMessage_thenShouldReturnMessage() {
        Message returned = messageService.addMessage(CONTENT, USER);
        assertThat(returned.getContent())
                .isEqualTo(message.getContent());
        assertThat(returned.getUser())
                .isEqualTo(message.getUser());
    }

}