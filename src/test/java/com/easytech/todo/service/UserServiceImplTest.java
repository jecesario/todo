package com.easytech.todo.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.reposity.UserRepository;
import com.easytech.todo.domain.service.UserServiceImpl;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import com.easytech.todo.service.utils.UserBuilder;
import com.easytech.todo.service.utils.UserBuilderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImp;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserResponse userResponse;

    @Test
    public void shouldReturnListOfUsers(){
        User user = UserBuilder.aUser().now();

        when(this.userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = this.userRepository.findAll();

        assertThat(users).isEqualTo(List.of(user));

    }

    @Test
    public void shouldCreateUser(){
        User userMock = UserBuilder.aUser().now();
        UserRequest userRequest = UserBuilderRequest.aUserRequest().nowRequest();
        when(this.userRepository.save(any(User.class))).thenReturn(userMock);
        when(this.passwordEncoder.encode(any())).thenReturn(userRequest.getPassword());

         this.userResponse = this.userServiceImp.create(userRequest);

        assertThat(userResponse).isNotNull();
    }
}
