package com.easytech.todo.rest.controller;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.service.UserService;
import com.easytech.todo.exceptions.AuthenticationFailureException;
import com.easytech.todo.exceptions.ObjectNotFoundException;
import com.easytech.todo.rest.controller.dto.CredentialsRequest;
import com.easytech.todo.rest.controller.dto.JwtTokenResponse;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import com.easytech.todo.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserControllerImpl(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

    @PostMapping(value = "/auth")
    public JwtTokenResponse auth(@RequestBody @Valid CredentialsRequest credentialsRequest) {
        try {
            User user = User.builder()
                    .withUsername(credentialsRequest.getUsername())
                    .withPassword(credentialsRequest.getPassword())
                    .build();

            userService.auth(user);

            String token = jwtService.generateToken(user);

            return JwtTokenResponse.builder()
                    .withType("Bearer")
                    .withToken(token)
                    .build();

        } catch (ObjectNotFoundException | AuthenticationFailureException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }
}
