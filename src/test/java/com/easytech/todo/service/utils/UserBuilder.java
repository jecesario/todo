package com.easytech.todo.service.utils;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.rest.controller.dto.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserBuilder {

    private User user;

    private  UserBuilder() {}

    public static UserBuilder aUser(){
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.user = new User();
        userBuilder.user.setId(1L);
        userBuilder.user.setUsername("denilson_teste");
        userBuilder.user.setEmail("denilson@teste.com");
        userBuilder.user.setPassword("123");
        userBuilder.user.setAdmin(false);

        return userBuilder;
    }

    public User now(){
        return this.user;
    }
}
