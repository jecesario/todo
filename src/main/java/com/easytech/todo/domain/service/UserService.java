package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.rest.controller.dto.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> findAll();
    User create(UserRequest userRequest);
    User update(Long id, UserRequest userRequest);
}
