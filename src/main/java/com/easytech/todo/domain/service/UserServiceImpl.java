package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.reposity.UserRepository;
import com.easytech.todo.exceptions.ObjectNotFoundException;
import com.easytech.todo.rest.controller.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Usuario com o id: %s não encontrado", id)));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }
}
