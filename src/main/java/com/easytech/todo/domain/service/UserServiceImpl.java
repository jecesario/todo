package com.easytech.todo.domain.service;

import com.easytech.todo.domain.model.User;
import com.easytech.todo.domain.reposity.UserRepository;
import com.easytech.todo.exceptions.AuthenticationFailureException;
import com.easytech.todo.exceptions.ObjectNotFoundException;
import com.easytech.todo.rest.controller.dto.UserRequest;
import com.easytech.todo.rest.controller.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = User
                .builder()
                .withUsername(userRequest.getUsername())
                .withEmail(userRequest.getEmail())
                .withPassword(passwordEncoder.encode(userRequest.getPassword()))
                .withAdmin(false)
                .build();

        User userPersisted = userRepository.save(user);

        return UserResponse
                .builder()
                .withId(userPersisted.getId())
                .withUsername(userPersisted.getUsername())
                .withEmail(userPersisted.getEmail())
                .withAdmin(userPersisted.isAdmin())
                .build();
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Usuario com o id: %s não encontrado", id)));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        User userPersisted = userRepository.save(user);

        return UserResponse
                .builder()
                .withId(userPersisted.getId())
                .withUsername(userPersisted.getUsername())
                .withEmail(userPersisted.getEmail())
                .build();
    }

    @Override
    public UserDetails auth(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

        if(passwordMatches) {
            return userDetails;
        }
        throw new AuthenticationFailureException("Ocorreu um erro ao autenticar usuário");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameContainingIgnoreCase(username)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Usuário com o username %s não encontrado!", username)));

        String[] roles = user.isAdmin()
                ? new String[] {"ADMIN", "USER"}
                : new String[] {"USER"};

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new ObjectNotFoundException(String.format("Usuario com id %s não encontrado", id));
        }
        userRepository.deleteById(id);
    }
}
