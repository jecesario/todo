package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    List<User> findAll();
    UserResponse create(UserRequest userRequest);
    UserResponse update(Long id, UserRequest userRequest);
    UserDetails auth(User user);
}
