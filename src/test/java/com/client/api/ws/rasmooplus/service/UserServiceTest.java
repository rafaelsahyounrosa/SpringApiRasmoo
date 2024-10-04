package com.client.api.ws.rasmooplus.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserDetailService userDetailsService;

    @Test
    void contextLoads() {

        userDetailsService.sendRecoveryCode("52918792a1@emailfoxi.pro");
    }
}
