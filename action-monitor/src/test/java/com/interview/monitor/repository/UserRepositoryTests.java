package com.interview.monitor.repository;

import com.interview.monitor.model.dao.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private final String USER_NAME = "test";
    private final String WRONG_USER_NAME = "wrong";

    @Before
    public void setUp() {
        testUser = new User();
        testUser.setName(USER_NAME);
        entityManager.persist(testUser);
        entityManager.flush();
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        User found = userRepository.findByName(testUser.getName());
        assertThat(found.getName())
                .isEqualTo(testUser.getName());
    }

    @Test
    public void whenFindByWrongName_thenReturnNull() {
        User notFound = userRepository.findByName(WRONG_USER_NAME);
        assertThat(notFound).isNull();
    }
}
