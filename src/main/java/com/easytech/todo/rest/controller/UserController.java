package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.service.UserService;
import com.easytech.todo.domain.service.UserServiceImpl;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll();

    @PostMapping
    public ResponseEntity<UserResponse> create(UserRequest userRequest);

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(Long id, UserRequest userRequest);
}
