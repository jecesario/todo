package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> findAll();
    User create(User user);
    User update(Long id, User user);
}
