package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.reposity.UserRepository;
import com.easytech.todo.exceptions.ObjectNotFoundException;
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
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User userChanged = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Usuario com o id: %s não encontrado", id)));
        return userRepository.save(userChanged);
    }
}
