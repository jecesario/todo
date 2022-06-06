package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.service.UserService;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserControllerImpl implements UserController {

    private UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.create(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.update(id, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
