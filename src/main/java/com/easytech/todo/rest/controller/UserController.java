package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.service.UserService;
import com.easytech.todo.domain.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll();

    @PostMapping
    public ResponseEntity<User> create(User user);

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(Long id, User user);
}
