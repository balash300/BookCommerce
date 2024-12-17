package com.books.controller;

import com.books.dto.UserDTO;
import com.books.dto.UserLoginDTO;
import com.books.model.Users;
import com.books.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody UserDTO userDTO) {
        Users registeredUser = userService.register(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        Users loggedInUser = userService.login(userLoginDTO);
        return ResponseEntity.ok(loggedInUser);
    }
}
