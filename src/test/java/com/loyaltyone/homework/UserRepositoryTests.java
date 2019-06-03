package com.loyaltyone.homework;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.loyaltyone.homework.data.UserRepository;
import com.loyaltyone.homework.entities.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
    
    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        User vishal = new User();
        vishal.setName("vishal");
        vishal.setCity("Sydney");
        entityManager.persist(vishal);
        entityManager.flush();
     
        // when
        Optional<User> found = userRepository.findByName(vishal.getName());
     
        // then
        assertThat(found.get().getName())
          .isEqualTo(vishal.getName());
    }

}
