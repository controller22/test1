package com.example.loginexample.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginexample.model.User;
import com.example.loginexample.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@MybatisTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void findAll() throws Exception {
        ObjectMapper om = new ObjectMapper(); // Jackson

        // when
        List<User> userlist = userRepository.findAll();
        // String responseBody = om.writeValueAsString(userlist);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(userlist.get(0).getUsername()).isEqualTo("ssar");
    }

    @Transactional
    @Test
    public void findById() throws Exception {
        ObjectMapper om = new ObjectMapper(); // Jackson
        int id = 1;
        // when
        User user = userRepository.findById(id);
        String responseBody = om.writeValueAsString(user);
        System.out.println("테스트 : " + responseBody);

        // then
        assertThat(user.getUsername()).isEqualTo("ssar");
    }
}
