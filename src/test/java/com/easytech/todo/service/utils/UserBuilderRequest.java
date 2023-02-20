package com.easytech.todo.service.utils;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.rest.controller.dto.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserBuilderRequest {

    private UserRequest userRequest;

    private UserBuilderRequest() {}

    public static UserBuilderRequest aUserRequest(){
        UserBuilderRequest userBuilder = new UserBuilderRequest();
        userBuilder.userRequest = new UserRequest("denilson_teste", "Deni@gmail.com", "123");

        return userBuilder;
    }

    public UserRequest nowRequest(){
        return this.userRequest;
    }
}
