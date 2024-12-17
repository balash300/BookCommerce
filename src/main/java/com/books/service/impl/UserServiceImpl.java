package com.books.service.impl;

import com.books.config.SecurityConfig;
import com.books.dto.UserDTO;
import com.books.dto.UserLoginDTO;
import com.books.model.Users;
import com.books.repository.UserRepository;
import com.books.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityConfig passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SecurityConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public Users register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("User already exists with this email.");
        }

        Users user = Users.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.passwordEncoder().encode(userDTO.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Users login(UserLoginDTO userLoginDTO) {
        Users user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.passwordEncoder().matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}