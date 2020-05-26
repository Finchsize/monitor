package com.interview.monitor.service;

import com.interview.monitor.model.dao.User;
import com.interview.monitor.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
public class UserServiceTests {

    private final String USER_NAME = "test";
    private final String WRONG_USER_NAME = "wrong";
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setName(USER_NAME);

        Mockito.when(userRepository.findByName(testUser.getName()))
                .thenReturn(testUser);

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        User found = userService.findUser(USER_NAME);
        assertThat(found.getName())
                .isEqualTo(USER_NAME);
    }

    @Test
    public void whenWrongName_thenUserShouldNotBeFound() {
        assertThrows(NoSuchElementException.class, () -> {
            userService.findUser(WRONG_USER_NAME);
        });
    }
}