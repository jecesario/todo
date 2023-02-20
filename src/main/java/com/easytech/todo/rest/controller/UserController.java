package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll();

    @PostMapping
    public ResponseEntity<UserResponse> create(UserRequest userRequest);

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(Long id, UserRequest userRequest);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(Long id);
}
